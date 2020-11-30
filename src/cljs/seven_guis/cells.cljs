(ns seven-guis.cells (:require [reagent.core :refer [atom]]
                               [instaparse.core :as insta :refer-macros [defparser]]
                               [clojure.walk :refer [postwalk]]))
(comment (ns seven-guis.cells (:require [instaparse.core :as insta])))

(defparser string->ast "src/cells.insta")

(def test-strings ["hello =1" "hello=$B1"])

(def cols (range 26))
(def letters (map (comp char #(+ 97 %)) cols))
(def rows (range 100))
(defn letter->idx [letter] (- (.charCodeAt letter 0) 65))

(def ast->ast
  (partial insta/transform
           {:number js/parseInt
            :op {"+" + "-" - "*" *}
            :ref
            (fn [col row]
              (vector :ref
                      (letter->idx (str col))
                      (js/parseInt row)))}))

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
        number (insta/transform {:discribed second} described)
        display (insta/transform {:discribed (fn [text number] (str text "=" number))}
                                 described)]
    (update-in state [:cells pos]
               #(assoc (assoc % :number number) :display display))))

;; This thing is beautiful!!!!!!
;; should convert to breadth first
(defn propagate [state pos]
  (let [state (evaluate state pos)]
    (reduce propagate state (get-in state [:cell pos :backlinks]))))

(defn add-backlinks [state target]
  (assoc state :cells
         (reduce (fn [cells link]
                   (assoc-in cells [link :backlinks]
                             (conj (or (get-in cells [link :backlinks]) #{}) target)))
                 (:cells state)
                 (:links (:cells state)))))

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
                            (if-let [ast (string->ast string)]
                              (let [ast (ast->ast ast)
                                    links (ast->links ast)
                                    _ (print ast links)
                                    state (remove-backlinks state pos)
                                    _ (print "first " state)
                                    state (update-in state [:cells pos] #(merge % {:raw string
                                                                                   :equation ast
                                                                                   :links links}))
                                    state (add-backlinks state pos)
                                    state (propagate state pos)]
                                state)
                              state))))

        cell (fn [value pos]
               ^{:key (pos 1)} [:td {:on-click #(swap! state assoc :editing pos)} value])
        editing-cell (fn [pos]
                       ^{:key (pos 1)} [:td#editing [:input {:ref #(set! (.-activeElement js/document) %)
                                                             :on-change #(set-cell pos (-> % .-target .-value))}]])]
    (print @state)
    (fn []
      [:div.cells
       [:table
        [:thead [:tr (for [letter letters] ^{:key letter} [:th letter])]]
        [:tbody
         (let [{cells :cells editing :editing} @state]
           (for [i rows]
             ^{:key i}
             [:tr [:td.row-header i]
              (for [j cols]
                ^{:key j} (if (= editing [i,j])
                            ^{:key j} [editing-cell [i,j]]
                            ^{:key j} [cell (get-in cells [[i j] :display]) [i j]]))]))]]])))
