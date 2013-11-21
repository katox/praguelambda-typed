(ns praguelambda-typed.repl
  (:require [clojure.core.typed :refer [cf ann ann-form print-env] :as t]))

;; type checking is *optional*
;; *local type inference* is used to infer
;; top-level annotations for clojure code

;; types only exist at compile time - Any (clj), java.lang.Number (java), ...
;; tags only exist at runtime - "rt types", invariants (ex. branching)

;; filters
;;  - trivial filters
;;      tt - true filter (can be)
;;      ff - impossible filter (cannot be)
;;  - positive and negative filters (0 = 0th argument)
;;      {:then (is Symbol 0), :else (! Symbol 0)}
;;  - latent filters
;;      filter sets attached to function types (instantiated later on)
;;      commonly used for typing predicates
;; filter sets - collections of filters

;; cf - check form

;; could be true but it is impossible to be false
(cf 1)

;; can never be true
(cf nil)

;; should be integer
(cf (inc 1))

;; is string or keyword value
(cf (if (:my-key {:nothing :here}) :found "not-found"))

;; is keyword value or nil
(cf (when (:my-key {:nothing :here}) :found))

;; what's this
(cf inc)

;; whatever goes in outputs number
(cf (fn my-inc [x]
      {:pre [(number? x)]}
      (inc x)))

;; try to ensure positive input
(cf (fn my-inc [x]
      {:pre [(pos? x)]}
      (inc x)))

;; try to ensure positive input number
(cf (fn my-inc [x]
      {:pre [(and (number? x) (pos? x))]}
      (inc x)))

;; check anonymous fn
(cf #(+ 1 %))

;; ann-form - annotate form

;; annote value with narrower type
(cf (ann-form 10 Integer))

;; union of number and string types, not number nor string itself
(cf (ann-form 10 (U Number String)))

;; in and out should be numbers
(cf (ann-form #(inc %) [Number -> Number]))

;; should be number or nil
(cf (ann-form (fn [x]
                (let [x1 (inc x)]
                  (when (> x1 10) x))) [Number -> (U nil  Number)]))

;; U nil Number - union of Number and nil
(cf (ann-form (fn [x]
                (let [x1 (inc x)]
                  (when (> x1 10) x))) [Number -> (U nil  Number)]))
;; Option Number - union of Number and nil
(cf (ann-form (fn [x]
                (let [x1 (inc x)]
                  (when (> x1 10) x))) [Number -> (t/Option  Number)]))
;; ann - annotate top level forms

;; check namespace - can't be used from within the defined ns
;(t/check-ns 'lambda-typed.core)

;; tc-ignore - ignore problematic, gradual addition of type annotations
