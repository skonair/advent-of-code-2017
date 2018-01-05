(ns clj-adventofcode.day10
  (:require [clojure.string :as str]))

(defn shift-right [ls n]
  (let [dn (- (count ls) (mod n (count ls)))]
    (concat (drop dn ls) (take dn ls))))

(defn shift-left [ls n]
  (let [dn (mod n (count ls))]
    (concat (drop dn ls) (take dn ls))))

(defn reverse-section [ls off len]
  (let [lls (shift-left ls off)]
    (shift-right
      (concat
        (reverse (take len lls))
        (drop len lls)) off)))

(defn run-round [input ls pos ss]
  (loop [l ls
         p pos
         s ss
         [i & is] input]
    (if (nil? i)
      [l p s]
      (recur
        (reverse-section l p i)
        (mod (+ p (+ s i)) (count l))
        (inc s)
        is))))

(defn knot-hash [ls input]
  (reduce * 1 (take 2 (first (run-round input ls 0 0)))))

(defn sparse-hash [ls input]
  (loop [[l pos skip-size] [ls 0 0]
         cnt 0]
    (if (>= cnt 64)
      l
      (recur (run-round input l pos skip-size) (inc cnt)))))

(defn dense-hash [ls input]
  (str/join
    (map #(format "%02x" %)
      (map #(apply bit-xor %)
        (partition 16
          (sparse-hash ls
            (concat (map int input) '(17 31 73 47 23))))))))

(def standard-list (range 256))
(def puzzle-input "46,41,212,83,1,255,157,65,139,52,39,254,2,86,0,204")

(println "Day 10, Part 1: "
         (knot-hash standard-list (map #(Integer/parseInt %) (str/split puzzle-input #","))))
;; 52070
(println "Day 10, Part 2: "
         (dense-hash standard-list puzzle-input))
;; 7f94112db4e32e19cf6502073c66f9bb