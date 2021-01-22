(ns seven-guis.crud
  (:require [reagent.core :refer [atom]]
            [clojure.string]
            [seven-guis.common :refer [basic-text-input atom-text-input]]
            [me.tonsky.persistent-sorted-set :as sset]))

;; current performance overview: names are stored in a sorted-set as strings, 
;; with last and first concatenated together like `last,first`
;; use rsubseq to get the sequence of people whos last names start with 
;; a prefix. Originally used sorted-set, but switched to tonsky's persistent-sorted-set for performance.
;;  
;; Currently works with 1,000,000 names. I am happy with this level of performance.
;; Just sending 10,000,000 names over https would be impractical. 
;; (i guess if there were enough John Smiths then it could be compressed ??)

(def sep "`")
(def li-height 26.4)
(def buffer "number of extra names to render" 40)

(defn find-prefix-range [array prefix]
  ())

(defn filter-people [{filter :filter people :people :as state}]
  (let [filter-gen
        (if (= filter "") (seq people)
            (let [last-idx (- (count filter) 1)
                  after-filter (str (subs filter 0 last-idx) (char (+ (.charCodeAt filter last-idx) 1)))]
              (sset/slice people filter after-filter)))]
    (merge state {:filtered-generator filter-gen :filtered-vector []})))

(defn scroll-load [{gen :filtered-generator vec :filtered-vector :as state} scroll]
  (let [names-to-load (+ buffer (quot scroll li-height))
        [extra gen] (split-at (- names-to-load (count vec)) gen)
        vec (into vec extra)]
    (merge state {:filtered-generator gen :filtered-vector vec})))

(defn index [state]
  (scroll-load (filter-people state) 0))

(defn add-person [{people :people first :first last :last :as state}]
  (let [person (str last sep first)]
    (-> state
        (assoc :filter "")
        (assoc :last "")
        (assoc :first "")
        (assoc :people (conj people person))
        (assoc :selected person)
        (index))))

(defn remove-person [{selected :selected people :people :as state}]
  (index (merge state {:selected nil :people (disj people selected)})))

(defn replace-person [state]
  (-> state
      (remove-person)
      (add-person)))

(defn render-person [state stored-name]
  (let [rendered-name (clojure.string/replace stored-name sep ",")]
    ^{:key stored-name}
    [:li {:on-click #(swap! state assoc :selected stored-name)
          :style {:background-color (when (= stored-name (:selected @state)) "blue")
                  :cursor "pointer"}}
     rendered-name]))

(defn crud []
  (let [state (atom {:people (sset/sorted-set)
                     :filtered-generator ()
                     :filtered-vector []
                     :scroll 0
                     :filter ""
                     :first ""
                     :last ""
                     :selected nil})]
    (fn []
      [:div
       [:div {:style {:display "flex" :flex-direction "row"}}
        [:div
         [basic-text-input
          "Filter Last Name:"
          (:filter @state)
          (fn [text] (swap! state (comp index #(assoc % :filter text))))]
         [:div {:style {:max-height "500px" :overflow "auto" :border "1px solid black"}
                :on-scroll #(swap! state scroll-load (-> % .-target .-scrollTop))}
          (doall (map (partial render-person state) (:filtered-vector @state)))]
         [:p "Scroll down to load more"]]
        [:div
         [atom-text-input "First Name:" state [:first]]
         [atom-text-input "Last Name:" state [:last]]]]
       [:div
        [:button
         {:on-click #(swap! state add-person)} "Create"]
        [:button {:on-click #(swap! state replace-person)} "Update"]
        [:button {:on-click #(swap! state remove-person)} "Delete"]
        [:button {:on-click #(swap! state (comp index (fn [state]
                                                        (assoc state :people
                                                               (apply sset/sorted-set (.map (js/Array.from (js/Array 1000000)) (fn [_ i a] (str i sep (+ i 842)))))))))} "Load 1,000,000 names"]]])))