(ns seven-guis.crud
  (:require [reagent.core :refer [atom]]
            [seven-guis.common :refer [basic-text-input atom-text-input]]))

(defn random-person []
  (let [random-name #(str (rand-int 100000))
        last (random-name)
        full (str last "," (random-name))]
    [last full]))

(defn update-trie
  "last name is key in trie, first name is added to the end"
  [trie last-name full-name]
  (update-in trie
             (conj (vec last-name) :full)
             #(conj (or % (sorted-set)) full-name)))

(defn init-trie [names]
  (reduce #(update-trie %1 (first %2) (second %2)) {} names))

(defn add-person [state]
  (let [last (:last state)
        full (str last "," (:first state))]
    (-> state
        (assoc :first "")
        (assoc :last "")
        (update :trie #(update-trie % last full)))))

(defn remove-person [state]
  (let [{last-name :last first-name :first} (:selected-person state)
        go (fn [node last-branch idx]
             (if (< idx (count last-name)) ()))]
    (update state :trie #(go % nil 0))))

(defn subtrie [trie last-name]
  (get-in trie (vec last-name)))

(defn render-person [full-name]
  ^{:key full-name}
  [:li full-name])

(defn list-people [trie]
  (if (set? trie)
    (map render-person trie)
    (mapcat list-people (vals trie))))


(defn crud []
  (let [state (atom {:trie {"7" {"4" {"2" {"3" {:first #{"52654"}}}}}
                            "6" {"2" {"7" {"7" {"9" {:first #{"47140"}}}}}}
                            "1" {"1" {"4" {"0" {"9" {:first #{"50681"}}}}}}
                            "3"
                            {"1" {"6" {"8" {"5" {:first #{"98525"}}}}}
                             "3" {"5" {"4" {"4" {:first #{"71572"}}}}}}}
                     :filter ""
                     :first ""
                     :last ""
                     :selected-person nil})]
    (fn []
      [:div
       [:div {:style {:display "flex" :flex-direction "row"}}
        [:div
         [atom-text-input "Filter Last Name:" state [:filter]]

         [:div {:style {:max-height "500px" :overflow "auto" :border "1px solid black"}}
          (let [{filter-string :filter
                 trie :trie
                 selected-person :selected-person} @state]
            (time (list-people (subtrie trie filter-string))))]]
        [:div
         [atom-text-input "First Name:" state [:first]]
         [atom-text-input "Last Name:" state [:last]]]]
       [:div
        [:button
         {:on-click #(swap! state add-person)}
         "Create"]
        [:button {:on-click #(swap! state (comp add-person remove-person))} "Update"]
        [:button {:on-click #(swap! state remove-person)} "Delete"]
        [:button {:on-click #(swap! state assoc :trie (init-trie (repeatedly 10000 random-person)))} "Load Test"]]])))
