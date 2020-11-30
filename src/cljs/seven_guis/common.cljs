(ns seven-guis.common)


(defn basic-text-input
  "a text input that directly updates an atom"
  [name atom path]
  (let [handle-input (fn [event]
                       (swap! atom assoc-in path (-> event .-target .-value)))]
    [:div [:label {:for name} name]
     [:input {:id name
              :value (get-in (deref atom) path)
              :on-input handle-input}]]))

