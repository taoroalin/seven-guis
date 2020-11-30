(ns seven-guis.timer (:require [reagent.core :refer [atom]]))

(defn timer []
  (let [tick-freq 10
        duration (atom 0.0)
        elapsed (atom 0.0)]
    (fn []
      (js/setTimeout #(when (< @elapsed @duration) (swap! elapsed (partial + (/ 1 tick-freq)))) (/ 1000 tick-freq))
      [:div {:style {:max-width 300}}
       [:div {:style {:background-color "darkgrey" :height "20px"}} [:div {:style {:height "100%" :width (str (min 100 (int (* (/ @elapsed @duration) 100))) "%") :background-color "blue"}}]]
       [:p (.toFixed @elapsed 2)]
       [:div {:style {:display "flex" :flex-direction "row"}} [:p "Duration:"]
        [:input {:type "range"
                 :value @duration
                 :max 20
                 :on-input #(reset! duration (-> % .-target .-value))}]]
       [:button {:style {:width "100%"} :on-click #(reset! elapsed 0)} "Reset"]])))