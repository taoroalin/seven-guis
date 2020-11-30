(ns seven-guis.temperature (:require [reagent.core :refer [atom]]))
(def celsius->fahrenheit #(+ 32 (* (/ 9 5) %)))
(def fahrenheit->celsius #(* (/ 5 9) (- % 32)))
(defn temperature-converter []
  (let [temperature (atom nil)]
    (fn []
      [:div
       [:input {:type "number" :on-change #(reset! temperature (-> % .-target .-value)) :value (.round js/Math @temperature)}] "Celsius ="
       [:input {:type "number" :on-change #(reset! temperature (fahrenheit->celsius (-> % .-target .-value))) :value (.round js/Math (celsius->fahrenheit @temperature))}] "Fahrenheit"])))