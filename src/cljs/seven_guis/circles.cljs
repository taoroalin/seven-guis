(ns seven-guis.circles (:require [reagent.core :refer [atom]]))

(def default-radius 50)
(def max-circles 100)
(def default-eternal {:filled? false})

;; state manipulation functions

(defn trim-eternal [eternal length]
  (vec (take max-circles (concat (take length eternal) (repeat default-eternal)))))

(defn drop-redos [{eternal :eternal undos :undos history :history :as state}]
  (let [new-history (drop undos history)]
    (merge state {:eternal (trim-eternal eternal (count (first new-history)))
                  :undos 0
                  :history new-history})))

(defn take-action [state func]
  (update (drop-redos state) :history #(cons (func (first %)) %)))

(defn add-circle [state circle]
  (take-action state #(conj % circle)))

(defn update-diameter [state id diameter]
  (take-action state #(assoc-in % [id :d] diameter)))

(defn undo [state]
  (let [new-undos (min (- (count (:history state)) 1) (inc (:undos state)))]
    (assoc state :undos new-undos)))

(defn redo [state]
  (let [new-undos (max 0 (dec (:undos state)))]
    (assoc state :undos new-undos)))

(defn modify-eternal [state func]
  (update state :eternal func))

(defn fill-circle [state id]
  (modify-eternal state #(assoc-in % [id :filled?] true)))

(defn current-time [{undos :undos history :history}]
  (nth history undos))

;; utils

(defn create-circle
  "calculate position of circle compared to parent"
  [event base-element]
  (let [mouse-x (.-clientX event)
        mouse-y (.-clientY event)
        base-rect (.getBoundingClientRect base-element)
        base-x (.-left base-rect)
        base-y (.-top base-rect)
        x (- mouse-x base-x)
        y (- mouse-y base-y)]
    {:d default-radius :x x :y y}))

;; sub components

(defn radius-picker [state id]
  [:input {:type "range"
           :auto-focus true
           :default-value 1
           :on-blur (fn [event]
                      (swap! state #(-> %
                                        (assoc :radius-picker nil)
                                        (update-diameter id (-> event .-target .-value)))))
           :style {:display "block" :postion "absolute" :top "30%" :text-align "center"}}])

(defn circle
  "render circle"
  [state id {x :x y :y d :d}]
  (println "updating" id)
  (let [r (/ d 2)
        top (- y r)
        left (- x r)]
    ^{:key id}
    [:div {:style {:position "absolute"
                   :top top
                   :left left
                   :min-width (str d "px")
                   :min-height (str d "px")
                   :border-radius 10000 ;; want max border radius for smooth curves
                   :background-color (when (get-in @state [:eternal id :filled?]) "lightgrey")
                   :border "2px solid black"}
           :on-click #(do (.stopPropagation %)
                          (swap! state fill-circle id))
           :on-context-menu #(do (.preventDefault %)
                                 (.log js/console %)
                                 (swap! state assoc :radius-picker id))}]))

(defn circles
  "undoing things is hard. it's also very fun. circles are fun too"
  []
  (let [state (atom {:history (list [{:x 100 :y 100 :d 100}])
                     :undos 0
                     :radius-picker nil
                     :eternal (vec (repeat max-circles default-eternal))})
        !canvas (atom nil)]
    (fn []
      (println @state)
      [:div {:style {:position "relative"}}
       (if-let [id (:radius-picker @state)] [radius-picker state id])
       [:div
        [:button {:on-click #(swap! state undo)} "Undo"]
        [:button {:on-click #(swap! state redo)} "Redo"]]
       [:div {:style {:width "500px"
                      :height "500px"
                      :position "relative"
                      :border "1px solid black"}
              :ref #(reset! !canvas %)
              :on-click #(swap! state add-circle (create-circle % @!canvas))}
        (map-indexed (fn [i v] ^{:key i} [circle state i v])
                     (current-time @state))]])))