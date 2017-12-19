(ns clj-adventofcode.day4
  (:require [clojure.string :as str]))

(defn passphrase-valid?
  "Checks whether a given passphrase is valid."
  [text]
  (let [words (str/split text #"\s")
        words-cnt (count words)
        words-set-cnt (count (set words))]
      (= words-cnt words-set-cnt)))

(defn passphrase-ext-valid?
  "Checks whether a given passphrase is valid."
  [text]
  (let [words-unsorted (str/split text #"\s")
        words (map #(str/join (sort %)) words-unsorted)
        words-cnt (count words)
        words-set-cnt (count (set words))]
    (= words-cnt words-set-cnt)))

(def passphrases-from-file
  (str/split-lines
    (slurp "resources/day4/passphrases.txt")))

(println "Day 4, Part 1: " (count (filter passphrase-valid? passphrases-from-file)))
(println "Day 4, Part 2: " (count (filter passphrase-ext-valid? passphrases-from-file)))
