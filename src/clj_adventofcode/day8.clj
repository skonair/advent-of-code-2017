(ns clj-adventofcode.day8
  (:require [clojure.string :as str]))

;; oui inc 550 if p <= 3
;; abh inc -189 if g == 0
;; kq inc 164 if znl != 7
;; kq dec 827 if oui == 550

(defn inc
  [v1 v2]
  (+ v1 v2))

(defn dec
  [v1 v2]
  (- v1 v2))

(defn !=
  [a b]
  (not (= a b)))

(defn call [this & that]
  (apply (resolve (symbol this)) that))

(defn exec-cond
  [registers entry]
  (let [var (get entry :var)
        op (get entry :op)
        param (get entry :param)
        c-op (get entry :c-op)
        c-var (get entry :c-var)
        c-param (get entry :c-param)
        v (get registers var)
        c-v (get registers c-var)]
    (if (call c-op c-v c-param)
      (assoc registers var (call op v param))
      registers)))

(defn parse-line
  [line]
  (let [[var op param i c-var c-op c-param] (re-seq #"\S+" line)]
    {:var var :op op :param (Integer/parseInt param) :c-var c-var :c-op c-op :c-param (Integer/parseInt c-param)} ))

(def lines-of-file
  (str/split-lines
    (slurp "resources/day8/instructions.txt")))

(def instructions (map parse-line lines-of-file))

(def registers
  (zipmap
    (map #(get % :var) instructions)
    (repeat 0)))

(defn run-inst
  [instructions registers]
  (loop [[i & is] instructions
         r registers]
    (if (nil? is)
      r
      (recur is (exec-cond r i) ))))

(defn highest-register
  [registers]
  (apply max (map #(val %) registers)))

(defn run-inst-highest
  [instructions registers]
  (loop [[i & is] instructions
         r registers
         highest 0]
    (if (nil? is)
      highest
      (let [new-reg (exec-cond r i)
            h (max highest (highest-register new-reg))]
      (recur is new-reg h)))))


(println "Day 8, Part 1: "
         (highest-register (run-inst instructions registers)))
;; 6061
(println "Day 8, Part 2: "
         (run-inst-highest instructions registers))
;;6696
