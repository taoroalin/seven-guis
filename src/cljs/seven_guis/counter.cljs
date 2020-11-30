(ns seven-guis.counter (:require [reagent.core :refer [atom]]))

(defn counter []
  (let [click-count (atom 0)]
    (fn []
      [:div
       "The button has been clicked "  @click-count " times."
       [:button {:on-click #(swap! click-count inc)} "Click me!"]])))