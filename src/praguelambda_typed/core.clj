(ns praguelambda-typed.core
  (:require [clojure.core.typed :refer [ann ann-form] :as t]))

(ann x Long)
(def x 10)

;; (def y 20N)
;; (ann y clojure.lang.BigInt)

;; (ann z Integer)
;; (def z 30)

;(ann v String)
;(def v 30)

;;(ann v String)
;;(def v (Integer/parseInt (str "4" "0")))

;; (ann w (U String Number))
;; (def w (Integer/parseInt (str "4" "0")))

;; (ann my-attempt-inc1 [Integer -> Integer])
;; (defn my-attempt-inc1 [x]
;;   (inc x))

;; (ann my-inc2 [Integer -> t/AnyInteger])
;; (defn my-inc2 [x]
;;   (inc x))

;; (ann my-inc3 [Integer -> Integer])
;; (defn my-inc3 [x]
;;   (int (inc x)))

;; (ann my-plus1 [Integer Integer -> Integer])
;; (defn my-plus1 [x y] (int (+ x y)))

;; use * to repeat the last type in declaration
;; (ann my-plus2 [Integer Integer * -> Integer])
;; (defn my-plus2 [x & y] (int (apply + x y)))

;; type aliases
;; (t/def-alias MyAnyInteger (U Integer 
;;                          Long 
;;                          clojure.lang.BigInt 
;;                          BigInteger 
;;                          Short 
;;                          Byte))
;; (ann my-inc4 [MyAnyInteger -> MyAnyInteger])
;; (defn my-inc4 [x]
;;   (inc x))

;(fn [] (my-inc4 "zzz"))


;; (ann my-failed-inc [MyAnyInteger -> MyAnyInteger])
;; (defn my-failed-inc [x]
;;   (str x))

;; can annotate stuff that belongs to someone else
;; (ann clojure.core/name [(U String clojure.lang.Named) -> String])


;; (ann my-name [(U String clojure.lang.Named) -> String])
;; (defn my-name [x]
;;   (name x))

;; (ann problematic-name [ -> String])
;; (defn problematic-name []
;;   (let [a (my-name :foo)
;;         b (my-name "bar")
;;         c (my-name nil)]))

;; type templates (useful for higher ordered fns)
;; (ann my-apply-f-to-p
;;      (All [x] [[x -> x] x -> x]))
;; (defn my-apply-f-to-p
;;   ([f p] (f p)))

;; (fn [] (my-apply-f-to-p even? 10))

;; multiple type clauses, tried in given order
;; (ann my-weird-get
;;      (All [x]
;;           (Fn [(t/Option (clojure.lang.ILookup Any x)) -> Number]
;;            [Any -> Number]
;;            [(t/Option (clojure.lang.ILookup Any x)) x -> (t/Option x)])))
;; (defn my-weird-get
;;   ([m] 100)
;;   ([m x] (get m x)))
;; (lambda-typed.core/my-weird-get {:a 1} :a)

;; print-env on different code paths
(let [coll {:a 20}] (if (empty? coll) (do (t/print-env "pr:") 10) (do (t/print-env "pr2:") (count coll))))
