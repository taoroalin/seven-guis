(ns seven-guis.crud
  (:require [reagent.core :refer [atom]]
            [seven-guis.common :refer [basic-text-input]]))

(defn remove-selected [state]
  (update-in state [:people] (partial filter (partial not= (:selected-person state)))))
(defn add-new [state]
  (-> state
      (assoc :first "")
      (assoc :last "")
      (update-in [:people]
                 (fn [people]
                   (conj people
                         {:first (:first state)
                          :last (:last state)})))))
(defn crud []
  (let [state (atom  {:people [{:first "tao" :last "lin"}]
                      :filter ""
                      :first ""
                      :last ""
                      :selected-person nil})]
    (fn []
      [:div
       [:div {:style {:display "flex" :flex-direction "row"}}
        [:div [basic-text-input "Filter String:" state [:filter]]
         (let [{filter-string :filter
                people :people
                selected-person :selected-person} @state]
           (for [person people
                 :when (= (subs (:last person) 0 (count filter-string))
                          filter-string)]
             ^{:key person}
             [:div {:style {:cursor "pointer" :background-color (when (= selected-person person) "blue")}
                    :on-click #(swap! state assoc :selected-person person)}
              (:first person) ", " (:last person)]))]
        [:div
         [basic-text-input "First Name:" state [:first]]
         [basic-text-input "Last Name:" state [:last]]]]
       [:div
        [:button
         {:on-click #(swap! state add-new)}
         "Create"]
        [:button {:on-click #(swap! state (comp add-new remove-selected))} "Update"]
        [:button {:on-click #(swap! state remove-selected)} "Delete"]]])))