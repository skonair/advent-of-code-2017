(ns clj-adventofcode.day2
  (:require [clojure.string :as str]))

(defn str-to-numberlist
  "Splits a string with numbers in it to a list of numbers."
  [row]
  (map #(Integer/parseInt %) (str/split row #"\s")))

(defn row-diff
  "Finds the min and max of a list of numbers and returns their difference."
  [row]
  (let [row-list (str-to-numberlist row)
        smallest (apply min row-list)
        largest (apply max row-list)]
        (- largest smallest)))

(defn spreadsheet-checksum
  "Computes the checksum of a spreadsheet for a given function."
  [spreadsheet row-fn]
  (let [rows (str/split spreadsheet #"\n")]
    (reduce + 0 (map row-fn rows))))

(defn spreadsheet-checksum-1
  "Computes the checksum of a spreadsheet."
  [spreadsheet]
  (spreadsheet-checksum spreadsheet row-diff ))

(defn dividable
  [v row]
  (/
    (reduce + 0
      (filter #(zero? (mod % v)) (remove #(= % v) row)))
    v))

(defn row-dividebles
  "Finds the two numbers that divide without rest and returns the result of the division."
  [row]
  (let [row-list (str-to-numberlist row)]
    (reduce + 0
      (map #(dividable % row-list) row-list ))))

(defn spreadsheet-checksum-2
  "Computes the checksum of a spreadsheet."
  [spreadsheet]
  (spreadsheet-checksum spreadsheet row-dividebles ))

(def spreadsheet (slurp "resources/day2/spreadsheet.txt"))

(println "Day 2, Part 1: " (spreadsheet-checksum-1 spreadsheet))
(println "Day 2, Part 2: " (spreadsheet-checksum-2 spreadsheet))
