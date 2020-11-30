(ns seven-guis.circles (:require [reagent.core :refer [atom]]))

(def default-radius 50)

(defn circle [{x :x y :y d :d filled? :filled?}]
  (let [r (/ d 2)
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

(defn create-circle [event base-element]
  (let [mouse-x (.-clientX event)
        mouse-y (.-clientY event)
        base-rect (.getBoundingClientRect base-element)
        base-x (.-left base-rect)
        base-y (.-top base-rect)
        x (- mouse-x base-x)
        y (- mouse-y base-y)]
    {:d default-radius :x x :y y :filled? false}))

(defn circles []
  (let [state (atom {:circles [{:x 100 :y 100 :d 100 :filled? true}]})
        !canvas (atom nil)]
    (fn []
      [:div [:div [:button {} "Undo"] [:button {} "Redo"]]
       [:div {:style {:width "500px"
                      :height "500px"
                      :position "relative"
                      :border "1px solid black"}
              :ref #(reset! !canvas %)
              :on-click (fn [event]
                          (swap! state update-in [:circles]
                                 #(conj % (create-circle event @!canvas))))}
        (map circle (:circles @state))]])))