(ns seven-guis.cells (:require [reagent.core :refer [atom]]))

(def cols (range 26))
(def letters (map (comp char #(+ 97 %)) cols))
(def rows (range 10))

(defn parse-equation [] ())

(defn cells []
  (let [state (atom {:text {[1 0] "1"
                            [2 0] "2"
                            [3 0] "3"}
                     :parsed {}
                     :equations {}
                     :backlinks {}
                     :editing [0 0]})
        cell (fn [value pos]
               ^{:key (pos 1)} [:td {:on-click #(swap! state assoc :editing pos)} value])
        editing-cell (fn [pos]
                       ^{:key (pos 1)} [:td.editing [:input {:on-change #()}]])]
    (print @state)
    (fn []
      [:div.cells
       [:table
        [:thead [:tr (for [letter letters] ^{:key letter} [:th letter])]]
        [:tbody
         (let [{text :text editing :editing} @state]
           (for [i rows]
             ^{:key i}
             [:tr [:td.row-header i]
              (for [j cols]
                (if (= editing [i,j])
                  [editing-cell [i,j]]
                  [cell (text [i j]) [i j]]))]))]]])))