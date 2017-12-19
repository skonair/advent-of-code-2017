(ns clj-adventofcode.day9
  (:require [clojure.string :as str]))

(defn filter-nots
  [ws]
  (.replaceAll ws "!." ""))

(defn filter-garbage
  [ws]
  (.replaceAll ws "<[^>]*>" ""))

(defn remove-garbage
  [ws]
  (.replaceAll ws "<[^>]*>" "<>"))

(defn score-lvl
  [line]
  (loop [ws line
         score 0
         lvl 1]
    (if (empty? ws)
      score
      (let [[w & wrest] ws]
        (cond
          (= \{ w) (recur wrest (+ score lvl) (inc lvl))
          (= \} w) (recur wrest score (dec lvl))
          true (recur wrest score lvl))))))

(defn score
  [ws]
  (score-lvl
    (filter-garbage
      (filter-nots ws))))

(defn removed-garbage
  [ws]
  (let [filtered-ws (filter-nots ws)]
    (- (count filtered-ws) (count (remove-garbage filtered-ws)))))

(def line-of-file (slurp "resources/day9/groups.txt"))


(println "Day 9, Part 1: " (score line-of-file))
;; 14212
(println "Day 9, Part 2: " (removed-garbage line-of-file))
;; 6569

