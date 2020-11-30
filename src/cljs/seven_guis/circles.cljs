(ns seven-guis.circles (:require [reagent.core :refer [atom]]))

(def default-radius 50)

(defn radius-picker []
  [:input {:type "range" :on-input #()}])

(defn circle [update id {x :x y :y d :d filled? :filled?}]
  (let [r (/ d 2)
        top (- y r)
        left (- x r)]
    ^{:key id}
    [:div {:style {:position "absolute"
                   :top top
                   :left left
                   :width d
                   :height d
                   :border-radius 100000
                   :background-color (when filled? "lightgrey")
                   :border "2px solid black"}
           :on-click #(update % id)}]))

(defn create-circle [event base-element]
  (let [mouse-x (.-clientX event)
        mouse-y (.-clientY event)
        base-rect (.getBoundingClientRect base-element)
        base-x (.-left base-rect)
        base-y (.-top base-rect)
        x (- mouse-x base-x)
        y (- mouse-y base-y)]
    {:d default-radius :x x :y y :filled? false}))

(defn drop-redos [{undos :undos history :history}]
  {:undos 0 :history (drop undos history)})


(defn circles []
  (let [state (atom {:history (list [{:x 100 :y 100 :d 100 :filled? false}])
                     :undos 0})
        !canvas (atom nil)]
    (fn []
      [:div [:div
             [:button {:on-click #(swap! state (fn [state]
                                                 (assoc state :undos (min (- (count (:history state)) 1)
                                                                          (inc (:undos state))))))}
              "Undo"]
             [:button {:on-click #(swap! state update :undos (fn [undos] (max 0 (dec undos))))} "Redo"]]
       [:div {:style {:width "500px"
                      :height "500px"
                      :position "relative"
                      :border "1px solid black"}
              :ref #(reset! !canvas %)
              :on-click (fn [event]
                          (let [circle (create-circle event @!canvas)]
                            (swap! state (fn [state]
                                           (let [state (drop-redos state)]
                                             (assoc state :history
                                                    (cons (conj (first (:history state)) circle)
                                                          (:history state))))))))}
        (map-indexed (partial circle
                              (fn [event id]
                                (.stopPropagation event)
                                (swap! state #(let [state (drop-redos %)]
                                                (assoc state :history (cons (assoc-in (first (:history state)) [id :filled?] true)
                                                                            (rest (:history state))))))))
                     (let [{history :history undos :undos} @state]
                       (nth history undos)))]])))