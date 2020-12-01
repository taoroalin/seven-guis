(ns seven-guis.common)



(defn basic-text-input
  "a text input calls a function on input"
  [name value update]
  [:div [:label {:for name} name]
   [:input {:id name
            :value value
            :on-input update}]])

(defn atom-text-input
  "a text input that directly updates an atom"
  [name atom path]
  [basic-text-input
   name
   (get-in (deref atom) path)
   (fn [event]
     (swap! atom assoc-in path (-> event .-target .-value)))])