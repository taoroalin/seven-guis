(ns seven-guis.temperature (:require [reagent.core :refer [atom]]))
(def celsius->fahrenheit #(+ 32 (* (/ 9 5) %)))
(def fahrenheit->celsius #(* (/ 5 9) (- % 32)))
(defn temperature-converter []
  (let [temp (atom {:c "" :f ""})]
    (fn []
      [:div
       [:input {:type "number"
                :on-input #(reset! temp (let [val (-> % .-target .-value)]
                                          {:c val :f (.round js/Math (celsius->fahrenheit val))}))
                :value (:c @temp)}]
       "Celsius ="
       [:input {:type "number"
                :on-input #(reset! temp (let [val (-> % .-target .-value)]
                                          {:f val :c (.round js/Math (fahrenheit->celsius val))}))
                :value (:f @temp)}]
       "Fahrenheit"])))