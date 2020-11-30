(ns seven-guis.prod
  (:require [seven-guis.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
