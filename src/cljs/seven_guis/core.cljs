(ns seven-guis.core
  (:require
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]
   [reagent.session :as session]
   [reitit.frontend :as reitit]
   [clerk.core :as clerk]
   [accountant.core :as accountant]
   [seven-guis.scratch :as scratch]
   [seven-guis.counter :refer [counter]]
   [seven-guis.temperature :refer [temperature-converter]]
   [seven-guis.flight :refer [flight-scheduler]]
   [seven-guis.timer :refer [timer]]
   [seven-guis.crud :refer [crud]]
   [seven-guis.circles :refer [circles]]
   [seven-guis.cells :refer [cells]]))

(defn box [name contents]
  [:div {:style {:border "1px solid darkgrey" :border-radius "10px" :padding "30px" :margin "20px"}} [:h3 name] contents])

(defn circles-page []
  (fn []
    [box "Circles" [circles]]))

(defn home-page []
  (fn []
    [:span.main
     [:h1 "Tao Lin's Seven GUIs"]
     [box "Counter" [counter]]
     [box "Temperature Converter" [temperature-converter]]
     [box "Flight Scheduler" [flight-scheduler]]
     [box "Timer" [timer]]
     [box "CRUD" [crud]]
     [box "Circles" [circles]]
     [box "Cells" [cells]]]))

;; -------------------------
;; Routes

(def router
  (reitit/router
   [["/" :index]
    ["/cells" :cells]
    ["/circles" :circles]
    ["/crud" :crud]
    ["/timer" :timer]
    ["/flight" :flight-scheduler]
    ["/temp" :temperature-converter]
    ["/counter" :counter]
    ["/about" :about]]))

(defn path-for [route & [params]]
  (if params
    (:path (reitit/match-by-name router route params))
    (:path (reitit/match-by-name router route))))

;; -------------------------
;; Page components



(defn items-page []
  (fn []
    [:span.main
     [:h1 "The items of seven-guis"]
     [:ul (map (fn [item-id]
                 [:li {:name (str "item-" item-id) :key (str "item-" item-id)}
                  [:a {:href (path-for :item {:item-id item-id})} "Item: " item-id]])
               (range 1 60))]]))


(defn item-page []
  (fn []
    (let [routing-data (session/get :route)
          item (get-in routing-data [:route-params :item-id])]
      [:span.main
       [:h1 (str "Item " item " of seven-guis")]
       [:p [:a {:href (path-for :items)} "Back to the list of items"]]])))


(defn about-page []
  (fn [] [:span.main
          [:h1 "About seven-guis"]]))


;; -------------------------
;; Translate routes -> page components

(defn page-for [route]
  (case route
    :circles #'circles-page
    :index #'home-page
    :about #'about-page
    :crud #'crud
    :cells #'cells
    :timer #'timer
    :flight #'flight-scheduler
    :temperature-converter #'temperature-converter
    :counter #'counter))


;; -------------------------
;; Page mounting component

(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [:div
       [:header
        [:p [:a {:href (path-for :index)} "Home"] " | "
         [:a {:href (path-for :about)} "About seven-guis"]]]
       [page]
       [:footer
        [:p "seven-guis was generated by the "
         [:a {:href "https://github.com/reagent-project/reagent-template"} "Reagent Template"] "."]]])))

;; -------------------------
;; Initialize app

(defn mount-root []
  (rdom/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (clerk/initialize!)
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (let [match (reitit/match-by-path router path)
            current-page (:name (:data  match))
            route-params (:path-params match)]
        (reagent/after-render clerk/after-render!)
        (session/put! :route {:current-page (page-for current-page)
                              :route-params route-params})
        (clerk/navigate-page! path)))
    :path-exists?
    (fn [path]
      (boolean (reitit/match-by-path router path)))})
  (accountant/dispatch-current!)
  (mount-root))
