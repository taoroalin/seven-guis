(ns seven-guis.crud
  "Strategy:
   1: only render elements on screen
   2: filter with trie
   filtering 100k takes 600ms
   "
  (:require [reagent.core :refer [atom]]
            [seven-guis.common :refer [basic-text-input atom-text-input]]))

(def li-height 26.4)

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
  (let [state (atom {:filtered-people []
                     :trie {}
                     :filter ""
                     :first ""
                     :last ""
                     :selected-person nil})
        update-forcer (atom 0)
        !box (clojure.core/atom nil)]
    (fn []
      ;; using extra atom to force rerender on scroll
      ;; should be better way
      @update-forcer
      [:div
       [:div {:style {:display "flex" :flex-direction "row"}}
        [:div
         [basic-text-input
          "Filter Last Name:"
          (:filter @state)
          #(swap! state (fn [state] (merge state {:filtered-people (vec (list-people (subtrie (:trie state) %)))
                                                  :filter %})))]
         [:div {:style {:max-height "500px" :overflow "auto" :border "1px solid black"}
                :ref #(when (not= @!box %) (reset! !box %))
                :on-scroll #(swap! update-forcer inc)}
          (let [{filtered-people :filtered-people
                 selected-person :selected-person} @state
                total-height (* li-height (count filtered-people))
                show-height (min (* li-height (count filtered-people)) (if @!box (.-offsetHeight @!box) 500))
                number-shown (min (count filtered-people) (int (/ show-height li-height)))
                max-scroll (- total-height show-height)
                scroll (if @!box (min max-scroll (.-scrollTop @!box)) 0)
                bottom (- total-height (+ scroll show-height))
                first-person (min (- (count filtered-people) number-shown) (int (/ scroll li-height)))
                last-person (min (count filtered-people) (+ first-person number-shown))]
            (list ^{:key 0} [:div {:style {:height (str scroll "px")}}]
                  ^{:key 1} (seq (subvec filtered-people first-person last-person))
                  ^{:key 2} [:div {:style {:height (str bottom "px")}}]))]]
        [:div
         [atom-text-input "First Name:" state [:first]]
         [atom-text-input "Last Name:" state [:last]]]]
       [:div
        [:button
         {:on-click #(swap! state add-person)}
         "Create"]
        [:button {:on-click #(swap! state (comp add-person remove-person))} "Update"]
        [:button {:on-click #(swap! state remove-person)} "Delete"]
        [:button {:on-click #(swap! state assoc :trie (time (init-trie (repeatedly 100000 random-person))))} "Load Test"]]])))
