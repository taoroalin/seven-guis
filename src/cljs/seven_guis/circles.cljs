(ns seven-guis.circles (:require [reagent.core :refer [atom]]))
(defn circle [{x :x y :y r :r filled? :filled?}]
  (let [d (* r 2)
        top (- y r)
        left (- x r)]
    [:div {:style {:position "absolute"
                   :top top
                   :left left
                   :width d
                   :height d
                   :border-radius 100000
                   :background-color (when filled? "lightgrey")
                   :border "2px solid black"}}]))
(defn circles []
  (let [state (atom {:undo-stack []
                     :redo-stack []
                     :circles [{:x 100 :y 100 :r 50 :filled? true}]})]
    (fn []
      [:div [:div [:button {} "Undo"] [:button {} "Redo"]]
       [:div {:style {:width "500px" :height "500px" :position "relative"}}
        (map circle (:circles @state))]])))