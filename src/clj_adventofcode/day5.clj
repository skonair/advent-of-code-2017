(ns clj-adventofcode.day5
  (:require [clojure.string :as str]))

(defn escaped?
  [offsets pos]
  (cond
    (< pos 0) true
    (>= pos (count offsets)) true
    :otherwise false))

(defn next-step
  "Computes the next offsets and position based on the current setting."
  [offsets-old pos-old]
  (let [offset (offsets-old pos-old)
        pos-new (+ offset pos-old)
        offsets-new (assoc offsets-old pos-old (inc offset))]
    [offsets-new pos-new]))

(defn next-step-strange
  "Computes the next offsets with strange behaviour."
  [offsets-old pos-old]
  (let [offset (offsets-old pos-old)
        pos-new (+ offset pos-old)
        offsets-new (assoc offsets-old pos-old (if (>= offset 3) (dec offset) (inc offset)))]
    [offsets-new pos-new]))

(defn exit-steps-func
  "Returns the steps until the exit is reached with a given next step function."
  [offsets next-step-func]
  (loop [offsets-current offsets
         pos-current 0
         cnt 0]
    (if (escaped? offsets-current pos-current)
      cnt
      (let [[offsets-new pos-new] (next-step-func offsets-current pos-current)]
        (recur offsets-new pos-new (inc cnt))))))

(defn exit-steps
  "Returns the steps until the exit is reached."
  [offsets]
  (exit-steps-func offsets next-step))

(defn exit-steps-strange
  "Returns the steps until the exit is reached."
  [offsets]
  (exit-steps-func offsets next-step-strange))

(def input-offset (into []
      (map #(Integer/parseInt %)
           (str/split-lines
             (slurp "resources/day5/jump-offsets.txt")))))

(println "Day 5, Part 1: " (exit-steps input-offset))
(println "Day 5, Part 2: " (exit-steps-strange input-offset))
