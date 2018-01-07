(ns clj-adventofcode.day14
  (:require [clojure.string :as str]
            [clj-adventofcode.day10 :as day10]))


(defn hexchar-to-bin [n]
  (case n
    \0 "0000"
    \1 "0001"
    \2 "0010"
    \3 "0011"
    \4 "0100"
    \5 "0101"
    \6 "0101"
    \7 "0111"
    \8 "1000"
    \9 "1001"
    \a "1010"
    \b "1011"
    \c "1100"
    \d "1101"
    \e "1110"
    \f "1111"
    :otherwise (throw (Exception. "Not a valid hex char."))))

(defn hex-to-bin [n]
  (loop [is n
         akk ""]
    (if (empty? is)
      akk
      (recur (rest is) (str akk (hexchar-to-bin (first is)))))))

(defn grid [k]
  (map #(hex-to-bin (day10/dense-hash (str k "-" %))) (range 128)))

(defn squares-used [grd]
  (reduce + 0 (map #(count (filter (fn [p1] (= \1 p1)) %)) grd)))

(println "Day 14, Part 1: " (squares-used (grid "uugsqrei")))
;; 8194