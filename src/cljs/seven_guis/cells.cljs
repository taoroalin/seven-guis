(ns seven-guis.cells (:require [reagent.core :refer [atom]]))



(def cols (range 26))
(def letters (map (comp char #(+ 97 %)) cols))
(def rows (range 100))

(defn parse-text [string] [string [] []])

(defn compute-value [state pos] state)

(defn propagate [state pos] state)

(defn add-backlinks [state links target]
  (assoc state :cells
         (reduce (fn [cells link]
                   (assoc-in cells [link :backlinks]
                             (conj (or (get-in cells [link :backlinks]) #{}) target)))
                 (:cells state)
                 links)))

(defn remove-backlinks [state pos]
  (assoc state :cells
         (reduce (fn [cells link]
                   (assoc-in cells [link :backlinks]
                             (disj (or (get-in cells [link :backlinks]) #{}) pos)))
                 (:cells state)
                 (get-in state [:cells pos :links]))))

(defn cells []
  (let [state (atom {:cells {[1 0] {:val 1 :raw "1"}
                             [2 0] {:val 1 :raw "2"}
                             [3 0] {:val 1 :raw "3"}}
                     :editing [0 0]})
        set-cell (fn [pos string]
                   (swap! state
                          (fn [state]
                            (let [[parsed links backlinks] (parse-text string)
                                  state (remove-backlinks state pos)
                                  state (assoc-in state [:links pos] links)
                                  state (add-backlinks state backlinks pos)
                                  state (assoc-in state [:cells pos] {:raw string
                                                                      :parsed parsed
                                                                      :val (compute-value state pos)})
                                  state (propagate state pos)]
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
                            ^{:key j} [cell (get-in cells [[i j] val]) [i j]]))]))]]])))
