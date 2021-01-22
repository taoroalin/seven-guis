(ns seven-guis.scratch
  (:require [instaparse.core :as insta :refer-macros [defparser]]))

(def example-lisp "(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)(lisp (and more (lisp) and more) lisp)")

(def example-roam "((blockref)) [[page link]] ^^highlight^^ $$latex$$https://example.com")

(defparser lisp-parser "seq = (list | word)+
                        list = <'('>seq<')'>
                        word=#\" *[^ \\(\\)]+ *\"")

(print (re-matches #"[^\[\]\{\}\(\)\!\*\_\^\$\:]+" "hello there (())"))
(js/console.log (.match "[[ho]]" #"^\[\["))

(defn lex [text]
  (let [token-types (list
                     {:re (new js/RegExp "^https?:\\/\\/([a-zA-Z]+\\.)?[a-zA-Z]+\\.[a-zA-Z]+(\\/[a-zA-Z]*)*(\\?([a-zA-Z]+=[a-zA-Z]+))*") :tag :url}
                     {:re #"^[^\[\]\{\}\(\)\!*_^$:]+" :tag :text}
                     {:re #"^\[\[" :tag :page-start}
                     {:re #"^\]\]" :tag :page-end}
                     {:re #"^\{\{" :tag :render-start}
                     {:re #"^\}\}" :tag :render-end}
                     {:re #"^\}\}" :tag :highlight}
                     {:re #"^\*\*" :tag :bold}
                     {:re #"^__" :tag :italic}
                     {:re #"^\$\$" :tag :latex}
                     {:re #"^\(\([0-9a-zA-Z\-\_]+\)\)" :tag :block})]
    (loop [i 0 text text tokens []]
      (if (== (count text) 0) tokens
          (let [match (loop [types token-types]
                        (if (not= () types) (let [match (.match text (:re (first types)))]
                                              (if match {:start i :end (+ i (count (first match))) :tag (:tag (first types))}
                                                  (recur (rest types)))) {:tag :text :start i :end (inc i)}))]
            (recur (:end match) (subs text (- (:end match) (:start match))) (conj tokens match)))))))


(println (time (last (repeatedly 1000 #(lex example-roam)))))


;; (time (apply + (map count (repeatedly 1000 #(lisp-parser example-lisp)))))

;; loop test
;; (def stime (.now js/performance))
;; (js/console.log (let [text "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "
;;                       range (list (range 10000))]
;;                   (reduce (fn [x1 x2]
;;                             (+ x1 (loop [i 0]
;;                                     (if (or (>= i x2) (>= i (count text)) (not= (nth text i) "a")) i (recur (inc i)))))) 0.0 range)))
;; (.log js/console (- (.now js/performance) stime))
;; simple lisp parser


;; (defn parse-lisp [text]
;;   (let [word-regex #"^ *[^\(\) ]+ *"
;;         _ (print (re-matches word-regex "hello hellie"))]
;;     (loop [text-left text tree [] idx-stack []]
;;       (let [match (re-matches word-regex text)
;;             _ (print match)]
;;         (if match (recur (subs text-left 0 1) (update-in tree idx-stack #(conj % "hello")) (update idx-stack (dec (count idx-stack)) inc))
;;             tree)))))

;; (time (parse-lisp example-lisp))

