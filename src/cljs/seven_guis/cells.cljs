(ns seven-guis.cells (:require [reagent.core :refer [atom]]
                               [instaparse.core :as insta :refer-macros [defparser]]
                               [clojure.walk :refer [postwalk]]))

(defparser string->ast "src/cells.insta")

(def test-strings ["hello =1" "hello=$B1"])

(def cols (range 26))
(def letters (map (comp char #(+ 97 %)) cols))
(def rows (range 100))
(defn letter->idx [letter] (- (.charCodeAt letter 0) 65))

(defn ast->ast [ast]
  (insta/transform
   {:number js/parseInt
    :op {"+" + "-" - "*" *}
    :ref
    (fn [letter number]
      (vector :ref
              (js/parseInt number)
              (letter->idx (str letter))))}
   ast))

(defn ast->links [ast]
  (let [smoosh (fn [& args] (into #{} (mapcat #(if (set? %) % #{}) args)))]
    (insta/transform {:ref (fn [col row] #{[col row]})
                      :toplevel smoosh
                      :described smoosh
                      :operation smoosh}
                     ast)))

(defn evaluate [state pos]
  (let [equation (get-in state [:cells pos :equation])
        get-number (fn [i j] (get-in state [:cells [i j] :number] 0))
        described (insta/transform
                   {:ref get-number
                    :operation (fn [a op b] (op a b))} equation)
        number (let [result (second (insta/transform {:discribed second} described))]
                 (if (number? result) result 0)) ;; improve
        display (second (insta/transform {:discribed (fn [text number] (str text "=" number))}
                                         described))]
    (update-in state [:cells pos]
               #(assoc (assoc % :number number) :display display))))

;; This thing is beautiful!!!!!!
;; should convert to breadth first
;; improve
(defn propagate [state pos]
  (let [state (evaluate state pos)]
    (reduce propagate state (get-in state [:cells pos :backlinks]))))

(defn add-backlinks [state pos]
  (assoc state :cells
         (reduce (fn [cells link]
                   (assoc-in cells [link :backlinks]
                             (conj (or (get-in cells [link :backlinks]) #{}) pos)))
                 (:cells state)
                 (get-in state [:cells pos :links]))))

(defn remove-backlinks [state pos]
  (assoc state :cells
         (reduce (fn [cells link]
                   (assoc-in cells [link :backlinks]
                             (disj (or (get-in cells [link :backlinks]) #{}) pos)))
                 (:cells state)
                 (get-in state [:cells pos :links]))))

(defn cells []
  (let [state (atom {:cells {[1 0] {:number 1 :raw "1" :display "1" :links #{} :backlinks #{}}}
                     :editing [0 0]})
        set-cell (fn [pos string]
                   (swap! state
                          (fn [state]
                            (let [ast (string->ast string)]
                              (if (insta/failure? ast)
                                state
                                (let [ast (ast->ast ast)
                                      links (ast->links ast)
                                      equation? (seq? (second ast))
                                      state (remove-backlinks state pos)
                                      state (update-in state [:cells pos] #(merge % {:raw string
                                                                                     :equation ast
                                                                                     :links links
                                                                                     :equation? equation?}))
                                      state (add-backlinks state pos)
                                      state (propagate state pos)]
                                  state))))))

        cell (fn [cell pos]
               ^{:key (pos 1)} [:td {:class (when (:equation? cell) "equation") :on-click #(swap! state assoc :editing pos)} (:display cell)])
        editing-cell (fn [cell pos]
                       ^{:key (pos 1)} [:td#editing [:input {:ref #(set! (.-activeElement js/document) %)
                                                             :default-value (:raw cell)
                                                             :on-change #(set-cell pos (-> % .-target .-value))}]])]
    (fn []
      (println "state" @state)
      [:div.cells
       [:p (str (ast->ast (string->ast "1")))]
       [:table
        [:thead [:tr [:th] (for [letter letters] ^{:key letter} [:th letter])]]
        [:tbody
         (let [{cells :cells editing :editing} @state]
           (for [i rows]
             ^{:key i}
             [:tr [:td.row-header i]
              (for [j cols]
                ^{:key j} (if (= editing [i,j])
                            ^{:key j} [editing-cell (cells [i,j]) [i,j]]
                            ^{:key j} [cell (cells [i,j]) [i j]]))]))]]])))


;;{:cells {[1 2] {:raw =1+B1, :equation [:toplevel [:operation 1 #object[cljs$core$_PLUS_] [:ref 1 1]]], :links #{[1 1]}, :equation? false, :number 3, :display 3}
;;         [:index 4] {:backlinks #{}}, [:column 1] {:backlinks #{}}, [:column 2] {:backlinks #{}}, [:index 0] {:backlinks #{}}, [:text =1+B] {:backlinks #{}}, [:index 1] {:backlinks #{}}, [:text =1+A] {:backlinks #{}}, [:reason [{:tag :regexp, :expecting #"^[0-9]{1,2}"}]] {:backlinks #{}}, [1 1] {:raw =1+A1, :equation [:toplevel [:operation 1 #object[cljs$core$_PLUS_] [:ref 1 0]]], :links #{[1 0]}, :equation? false, :number 2, :display 2, :backlinks #{[1 2]}}, [:column 5] {:backlinks #{}}, [:text =1+] {:backlinks #{}}, [:line 1] {:backlinks #{}}, [:index 3] {:backlinks #{}}, [:text] {:backlinks #{}}, [2 1] {:backlinks #{}}, [1 0] {:number 0, :raw 2, :display 2, :links #{}, :backlinks #{[1 1]}, :equation [:toplevel 2], :equation? true}, [2 0] {:backlinks #{}}, [:reason [{:tag :string, :expecting =} {:tag :regexp, :expecting #"^[^=]+"}]] {:backlinks #{}}, [:text =] {:backlinks #{}}, [:reason [{:tag :regexp, :expecting #"^[A-Z]"} {:tag :regexp, :expecting #"^\d+"}]] {:backlinks #{}}, [:text =+] {:backlinks #{}}, [:column 4] {:backlinks #{}}}, :editing [1 0]}