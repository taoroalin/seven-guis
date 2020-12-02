(ns seven-guis.cells (:require [reagent.core :refer [atom]]
                               [clojure.string :as string]
                               [instaparse.core :as insta :refer-macros [defparser]]
                               [clojure.walk :refer [postwalk]]))
;; Constants
(def cols 26)
(def rows 100)
(def letters (map (comp char #(+ 97 %)) (range cols)))
(def test-strings ["hello =1" "hello=$B1"])
(def example-csv
  "Range,Pairs
1,sum=A2+A3
2,sum=A3+A4
3,sum=A4+A5
4,sum=A2+A4
5,sum=A3+A5
6,sum=A4+A6
7,sum=A2+A5
8,sum=A3+A6
9,sum=A4+A7
10,sum=A2+A6
11,sum=A3+A7
12,sum=A4+A8
13,sum=A2+A7
14,sum=A3+A8")
(def example-csv-mid "Range,Consecutive Sum,t1,t2
  1,sum=A2+A3,1,2
  2,sum=A3+A4,2,3
  3,sum=A4+A5,3,4
  4,sum=A2+A4,4,5
  5,sum=A3+A5,5,6
  6,sum=A4+A6,6,7
  7,sum=A2+A5,7,8
  8,sum=A3+A6,8,9
  9,sum=A4+A7,9,10
  10,sum=A2+A6,10,11
  11,sum=A3+A7,11,12
  12,sum=A4+A8,12,13
  13,sum=A2+A7,13,14
  14,sum=A3+A8,14,15
  15,sum=A2+A4,15,16
  16,sum=A3+A5,16,17
  17,sum=A4+A6,17,18
  18,sum=A2+A5,18,19
  19,sum=A3+A6,19,20
  20,sum=A4+A7,20,21
  21,sum=A2+A6,21,22
  22,sum=A3+A7,22,23
  23,sum=A4+A8,23,24
  24,sum=A2+A7,24,25
  25,sum=A3+A8,25,26
  26,sum=A4+A9,26,27
  27,sum=A2+A8,27,28
  28,sum=A3+A9,28,29
  29,sum=A2+A5,29,30
  30,sum=A3+A6,30,31
  31,sum=A4+A7,31,32
  32,sum=A2+A6,32,33
  33,sum=A3+A7,33,34
  34,sum=A4+A8,34,35
  35,sum=A2+A7,35,36
  36,sum=A3+A8,36,37
  37,sum=A4+A9,37,38
  38,sum=A2+A8,38,39
  39,sum=A3+A9,39,40
  40,sum=A4+A10,40,41
  41,sum=A2+A9,41,42
  42,sum=A3+A10,42,43
  43,sum=A2+A6,43,44
  44,sum=A3+A7,44,45
  45,sum=A4+A8,45,46
  46,sum=A2+A7,46,47
  47,sum=A3+A8,47,48
  48,sum=A4+A9,48,49
  49,sum=A2+A8,49,50
  50,sum=A3+A9,50,51
  51,sum=A4+A10,51,52
  52,sum=A2+A9,52,53
  53,sum=A3+A10,53,54
  54,sum=A4+A11,54,55
  55,sum=A2+A10,55,56
  56,sum=A3+A11,56,57
  57,sum=A2+A7,57,58
  58,sum=A3+A8,58,59
  59,sum=A4+A9,59,60
  60,sum=A2+A8,60,61
  61,sum=A3+A9,61,62
  62,sum=A4+A10,62,63
  63,sum=A2+A9,63,64
  64,sum=A3+A10,64,65
  65,sum=A4+A11,65,66
  66,sum=A2+A10,66,67
  67,sum=A3+A11,67,68
  68,sum=A4+A12,68,69
  69,sum=A2+A11,69,70
  70,sum=A3+A12,70,71
  71,sum=A2+A8,71,72
  72,sum=A3+A9,72,73
  73,sum=A4+A10,73,74
  74,sum=A2+A9,74,75
  75,sum=A3+A10,75,76
  76,sum=A4+A11,76,77
  77,sum=A2+A10,77,78
  78,sum=A3+A11,78,79
  79,sum=A4+A12,79,80
  80,sum=A2+A11,80,81
  81,sum=A3+A12,81,82
  82,sum=A4+A13,82,83
  83,sum=A2+A12,83,84
  84,sum=A3+A13,84,85
  85,sum=A2+A9,85,86
  86,sum=A3+A10,86,87
  87,sum=A4+A11,87,88
  88,sum=A2+A10,88,89
  89,sum=A3+A11,89,90
  90,sum=A4+A12,90,91
  91,sum=A2+A11,91,92
  92,sum=A3+A12,92,93
  93,sum=A4+A13,93,94
  94,sum=A2+A12,94,95
  95,sum=A3+A13,95,96
  96,sum=A4+A14,96,97
  97,sum=A2+A13,97,98
  98,sum=A3+A14,98,99")

;; generate parser from EBNF at compile time
(defparser string->ast "src/cells.insta" :auto-whitespace :standard)

(defn letter->idx [letter]
  (- (.charCodeAt letter 0) (if (= (.toUpperCase letter) letter) 65 97)))


(defn clean-ast
  "convert numbers and operations to their own types, and convert letter cols to numbers"
  [ast]
  (insta/transform
   {:number js/parseInt
    :op {"+" + "-" - "*" * "/" /}
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
                                        :equation ast
                                        :display string
                                        :links links}))
            state (add-backlinks state pos)]
        state))))

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
                                     :syntax-error? (if (= (count string) 0)
                                                      false true)}))
               (let [ast (clean-ast ast)
                     _ (println ast)
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

(defn paste-csv [{cells :cells [row-e col-e] :editing :as state} csv]
  (let [lines (mapv #(vec (string/split % ",")) (string/split csv "\n"))
        rows (count lines)
        cols (apply max (map count lines))
        updates (for [row (range rows) col (range cols)]
                  [[(+ row row-e) (+ col col-e)]
                   (get-in lines [row col])])
        state (reduce #(apply add-cell %1 %2) state updates)]
    state))

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
                             :on-paste #(do (println "pasting") (.preventDefault %) (swap! state paste-csv (.getData (.-clipboardData %) "text")))
                             :on-change #(set-cell state pos (-> % .-target .-value))
                             :on-key-down #(handle-key state pos %)}]])]
    (fn []
      (time (paste-csv @state example-csv-mid))
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
