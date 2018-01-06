(ns clj-adventofcode.day11
  (:require [clojure.string :as str]))

(defn dist [[x y]]
  (int (+ (Math/abs x) (max (- (Math/abs y) (/ (Math/abs x) 2)), 0))))

(defn coords [[n s ne sw nw se]]
  [(- (+ ne se) (+ nw sw)) (- (+ n (* 0.5 ne) (* 0.5 nw)) (+ s (* 0.5 se) (* 0.5 sw)))])

(defn process-entries [l]
  (map #(if (nil? %) 0 %) l))

(defn steps [path]
  (-> path
      frequencies
      (map ["n" "s" "ne" "sw" "nw" "se"])
      process-entries
      coords
      dist))

(defn recl [lst]
  (loop [l lst
         akku []]
    (if (empty? l)
      akku
      (recur (butlast l) (conj akku l)))))

(defn furthest-away [path]
  (reduce max (map steps (recl path))))

(def line-of-file (str/split (slurp "resources/day11/path.txt") #","))

(println "Day 11, Part 1: " (steps line-of-file))
;; 834
(println "Day 11, Part 2: " (furthest-away line-of-file))
;; 1569
