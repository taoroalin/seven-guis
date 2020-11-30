(ns seven-guis.flight (:require [reagent.core :refer [atom]]))

(defn flight-scheduler []
  (let [flight-type (atom "one-way flight")
        start (atom nil)
        end (atom nil)]
    (fn []
      (print @flight-type @start @end)
      [:div {:style {:display "flex" :flex-direction "column" :max-width 200}}
       [:select {:value @flight-type
                 :on-change #(reset! flight-type (-> % .-target .-value))}
        [:option "one-way flight"]
        [:option "return flight"]]
       [:input {:type "date"
                :valueAsDate @start
                :on-change #(reset! start (-> % .-target .-valueAsDate))}]
       [:input {:type "date"
                :valueAsDate @end
                :on-change #(reset! end (-> % .-target .-valueAsDate))
                :disabled (= @flight-type "one-way flight")}]
       [:button {:on-click #(js/alert (str "You have booked a " @flight-type " on " @start (if (= @flight-type "return flight") (str " returning on " @end ".") ".")))
                 :disabled (and (= @flight-type "return flight") (> @start @end))} "Book"]])))
