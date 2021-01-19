(ns seven-guis.cells (:require [reagent.core :refer [atom] :as reagent]
                               [clojure.string :as string]
                               [cljs.pprint :refer [pprint write]]
                               [seven-guis.examples :refer [example-csv-fibonacci example-csv-conv]]
                               [instaparse.core :as insta :refer-macros [defparser]]
                               [clojure.walk :refer [postwalk]]))


;; Constants & non-state utils
(def cols 26)
(def rows 100)
(def timeout 2000)
(def letters (map (comp char #(+ 97 %)) (range cols)))
(def test-strings ["hello =1" "hello=$B1"])
(defn letter->idx [letter]
  (- (.charCodeAt letter 0) (if (= (.toUpperCase letter) letter) 65 97)))
(defn copy-text [text]
  #(.writeText (.-clipboard js/navigator) text))

;; parsing equations and manipulating ast

;; generate parser from EBNF at compile time
(defparser string->ast "src/cells.insta" :auto-whitespace :standard)
(defn clean-ast
  "convert numbers and operations to their own types, and convert letter cols to numbers"
  [ast]
  (let [ast (insta/transform
             {:number js/parseInt
              :ref
              (fn [letter number]
                (vector :ref
                        (js/parseInt number)
                        (letter->idx (str letter))))}
             ast)]
    ast))

(defn ast->links
  "scrape all cell refs from the ast into a set"
  [ast]
  (let [smoosh (fn [& args] (into #{} (mapcat #(if (set? %) % #{}) args)))]
    (insta/transform {:ref (fn [col row] #{[col row]})
                      :toplevel smoosh
                      :described smoosh
                      :naked-operation smoosh}
                     ast)))

;; State transition functions
(defn add-backlinks [state pos]
  (assoc state :cells
         (reduce (fn [cells link]
                   (assoc-in cells [link :backlinks]
                             (conj (or (get-in cells [link :backlinks]) #{}) pos)))
                 (:cells state)
                 (get-in state [:cells pos :links]))))

(defn remove-backlinks
  "remove backlinks from old equation"
  [state pos]
  (assoc state :cells
         (reduce (fn [cells link]
                   (assoc-in cells [link :backlinks]
                             (disj (or (get-in cells [link :backlinks]) #{}) pos)))
                 (:cells state)
                 (get-in state [:cells pos :links]))))

(defn add-cell
  "Adds cell from string, including ast, backlinks"
  [state pos string]
  (let [ast (string->ast string)]
    (if (insta/failure? ast)
      (update-in state [:cells pos]
                 #(merge % {:raw string
                            :display string}))
      (let [ast (clean-ast ast)
            links (ast->links ast)
            state (remove-backlinks state pos)
            state (update-in state [:cells pos]
                             #(merge % {:raw string
                                        :ast ast
                                        :display string
                                        :links links}))
            state (add-backlinks state pos)
            state (update state :dirty #(conj % pos))]
        state))))

(defn parse-string
  "Adds cell from string, including ast, backlinks"
  [string]
  (let [ast (string->ast string)]
    (if (insta/failure? ast) {}
        (let [ast (clean-ast ast)
              links (ast->links ast)]
          {:ast ast :links links}))))

(defn handle-key [state-atom pos event]
  (let [key (.-key event)
        shift (.-shiftKey event)
        move-direction (case key
                         ("Tab") (if shift [0 -1] [0 1])
                         ("ArrowDown" "Enter") [1 0]
                         ("ArrowUp") [-1 0]
                         [0 0])
        constrain #(max 0 (min %2 %1))
        constrain-pos (fn [[i j]] [(constrain i rows) (constrain j cols)])
        new-editing (constrain-pos (mapv + pos move-direction))]
    (when (not= new-editing pos)
      (swap! state-atom assoc :editing new-editing))))

(defn paste-csv [{[row-e col-e] :editing :as state} csv]
  (let [lines (mapv #(vec (string/split % ",")) (string/split csv "\n"))
        rows (count lines)
        cols (apply max (map count lines))
        updates (for [row (range rows) col (range cols)]
                  [[(+ row row-e) (+ col col-e)]
                   (get-in lines [row col])])
        parsed (time (for [row (range rows) col (range cols)]
                       (parse-string (get-in lines [row col]))))
        state (reduce #(apply add-cell %1 %2) state updates)]
    (if (= 1 1) state parsed)))

(defn trace-dirty
  "Propagate dirty status to dependents"
  [{dirty :dirty :as state}]
  (loop [todo dirty done #{}]
    (if (empty? todo) (assoc state :dirty done)
        (let [cur (first todo)
              new-done (conj done cur)
              deps (get-in state [:cells cur :backlinks])
              novel-deps (filter (comp not done) deps)
              new-todo (into (disj todo cur) novel-deps)]
          (recur new-todo new-done)))))

(defn evaluate [state pos]
  (let [equation (get-in state [:cells pos :ast])
        get-number (fn [i j] (get-in state [:cells [i j] :number] 0))
        described (insta/transform
                   {:ref get-number
                    :op {"+" + "-" - "*" * "/" /}
                    :naked-operation (fn [a op b] (op a b))} equation)
        number (let [result (second (insta/transform {:described #(identity %2)} described))]
                 result) ;; improve
        display (second (insta/transform {:described (fn [text number] (str text "=" number))}
                                         described))]
    (update-in state [:cells pos]
               #(assoc % :number number :display display))))

(defn evaluate-cells [state]
  (let [state (trace-dirty state)
        to-dirty-pair (fn [state pos] [(count (filter (:dirty state) (get-in state [:cells pos :links]))) pos])
        by-dirty (into (sorted-set)
                       (map #(to-dirty-pair state %))
                       (:dirty state))
        dirty-map (into {} (map (comp vec reverse)) by-dirty)]
    (loop [by-dirty by-dirty dirty-map dirty-map state state]
      (if (empty? by-dirty) (assoc state :dirty #{})
          (if (> 0 (first (first by-dirty))) (do (js/alert "infinite loop. please remove") (assoc state :dirty #{}))
              (let [cur-pair (first by-dirty)
                    by-dirty (disj by-dirty cur-pair)
                    cur (second cur-pair)
                    dirty-map (update dirty-map cur dec)
                    backlinks (get-in state [:cells cur :backlinks])
                    by-dirty (reduce #(conj (disj %1 [(dirty-map %2) %2]) [(dec (dirty-map %2)) %2])
                                     by-dirty
                                     backlinks)
                    dirty-map (reduce #(update %1 %2 dec) dirty-map backlinks)
                    state (evaluate state cur)]
                (recur by-dirty dirty-map state)))))))

;; rendering
(defn cell [state cell pos]
  ^{:key (pos 1)} [:td {:class [(when (:syntax-error? cell) "syntax-error") (when (:ast? cell) "equation")]
                        :on-click #(swap! state assoc :editing pos)}
                   (:display cell)])

(defn editing-cell [state cell pos]
  ^{:key (pos 1)}
  [:td#editing
   [:input {:auto-focus true
            :default-value (:raw cell)
            :on-paste #(do (.preventDefault %)
                           (swap! state paste-csv (.getData (.-clipboardData %) "text")))
            :on-change #(swap! state add-cell pos (-> % .-target .-value))
            :on-key-down #(handle-key state pos %)}]])

(defn cells
  "it's a spreadsheet. it's virtue is it's less code than real spreadsheets"
  []
  (let
   [state (atom {:cells {[1 0] {:ast [:toplevel 1] :number 1 :raw "1" :display "1" :links #{} :backlinks #{}}}
                 :dirty #{} ;; pos's ([0 0]) that need to be evaluated
                 :editing [0 0]})]
    (reagent/create-class
     {:component-did-update #(when (not-empty (:dirty @state)) (swap! state evaluate-cells))
      :reagent-render
      (fn []
        ;;(write @state)
        [:div.cells
         [:p "Type an equation into the grid." [:br]
          "it supports arithmetic, will support ranges soon" [:br]
          "Here's an example: " [:code "result = A1+A2+1"]]
         [:p "Navigate with Tab, Shift+Tab, Enter, and UpArrow"]
         [:p "You can paste csv into it too"]
         [:button {:on-click (copy-text example-csv-fibonacci)} "Copy Fibonacci CSV"]
         [:button {:on-click (copy-text example-csv-conv)} "Copy Convolution CSV"]
         [:table
          [:thead [:tr [:th] (for [letter letters] ^{:key letter} [:th letter])]]
          [:tbody
           (let [{cells :cells editing :editing} @state]
             (for [i (range rows)]
               ^{:key i}
               [:tr [:td.row-header i]
                (for [j (range cols)]
                  ^{:key j} (if (= editing [i,j])
                              ^{:key j} [editing-cell state (cells [i,j]) [i,j]]
                              ^{:key j} [cell state (cells [i,j]) [i j]]))]))]]])})))