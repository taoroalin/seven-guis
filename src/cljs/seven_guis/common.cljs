(ns seven-guis.common)

(defn basic-text-input [name atom path]
  [:div [:label {:for name} name]
   [:input {:id name :value (get-in (deref atom) path) :on-input (fn [event] (swap! atom #(assoc-in % path (-> event .-target .-value))))}]])