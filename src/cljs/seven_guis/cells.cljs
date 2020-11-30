(ns seven-guis.cells (:require [reagent.core :refer [atom]]
                               [instaparse.core :as insta :refer-macros [defparser]]
                               [clojure.walk :refer [postwalk]]))
;; Constants
(def cols 26)
(def rows 100)
(def letters (map (comp char #(+ 97 %)) (range cols)))
(def test-strings ["hello =1" "hello=$B1"])

;; generate parser from EBNF at compile time
(defparser string->ast "src/cells.insta" :auto-whitespace :standard)

(defn letter->idx [letter] (- (.charCodeAt letter 0) (if (= (.toUpperCase letter) letter) 65 97)))


(defn clean-ast
  "convert numbers and operations to their own types, and convert letter cols to numbers"
  [ast]
  (insta/transform
   {:number js/parseInt
    :op {"+" + "-" - "*" *}
    :ref
    (fn [letter number]
      (vector :ref
              (js/parseInt number)
              (letter->idx (str letter))))}
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

(defn evaluate [state pos]
  (let [equation (get-in state [:cells pos :equation])
        get-number (fn [i j] (get-in state [:cells [i j] :number] 0))
        described (insta/transform
                   {:ref get-number
                    :naked-operation (fn [a op b] (op a b))} equation)
        number (let [result (second (insta/transform {:discribed second} described))]
                 (if (number? result) result 0)) ;; improve
        display (second (insta/transform {:discribed (fn [text number] (str text "=" number))}
                                         described))]
    (update-in state [:cells pos]
               #(assoc (assoc % :number number) :display display))))

;; Propagating with one reduction is beautiful!
;; could improve to wait for all dependencies to update to improve performance
(defn propagate [state pos seen]
  (if (seen pos) (do (js/alert "you have created an infinite loop. please change it back") state)
      (let [state (evaluate state pos)
            seen (conj seen pos)
            propagate #(propagate %1 %2 seen)]
        (reduce propagate state (get-in state [:cells pos :backlinks])))))

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

(defn set-cell
  "this function is in charge of swapping the cells after input.
   it takes a string all the way to a parsed, 
   evaluated, and propagated equation"
  [state pos string]
  (swap! state
         (fn [state]
           (let [ast (string->ast string)]
             (if (insta/failure? ast)
               (update-in state [:cells pos]
                          #(merge % {:raw string
                                     :display string
                                     :syntax-error? true}))
               (let [ast (clean-ast ast)
                     links (ast->links ast)
                     equation? (coll? (second ast))
                     state (remove-backlinks state pos)
                     state (update-in state [:cells pos]
                                      #(merge % {:raw string
                                                 :syntax-error? false
                                                 :equation ast
                                                 :links links
                                                 :equation? equation?}))
                     state (add-backlinks state pos)
                     state (propagate state pos #{})]
                 state))))))

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

(defn cells
  "it's a spreadsheet. it's virtue is it's less code than real spreadsheets"
  []
  (let
   [state (atom {:cells {[1 0] {:number 1 :raw "1" :display "1" :links #{} :backlinks #{}}}
                 :editing [0 0]})
    cell (fn [cell pos]
           ^{:key (pos 1)} [:td {:class [(when (:syntax-error? cell) "syntax-error") (when (:equation? cell) "equation")]
                                 :on-click #(swap! state assoc :editing pos)}
                            (:display cell)])
    editing-cell (fn [cell pos]
                   ^{:key (pos 1)}
                   [:td#editing
                    [:input {:auto-focus true
                             :default-value (:raw cell)
                             :on-change #(set-cell state pos (-> % .-target .-value))
                             :on-key-down #(handle-key state pos %)}]])]
    (fn []
      ;;(println "state" @state)
      [:div.cells
       [:p "Type an equation into the grid." [:br]
        "it supports arithmetic, will support ranges soon" [:br]
        "Here's an example: " [:code "result = A1+A2+1"]]
       [:p "Navigate with Tab, Shift+Tab, Enter, and UpArrow"]
       [:table
        [:thead [:tr [:th] (for [letter letters] ^{:key letter} [:th letter])]]
        [:tbody
         (let [{cells :cells editing :editing} @state]
           (for [i (range rows)]
             ^{:key i}
             [:tr [:td.row-header i]
              (for [j (range cols)]
                ^{:key j} (if (= editing [i,j])
                            ^{:key j} [editing-cell (cells [i,j]) [i,j]]
                            ^{:key j} [cell (cells [i,j]) [i j]]))]))]]])))