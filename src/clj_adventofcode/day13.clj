(ns clj-adventofcode.day13
  (:require [clojure.string :as str]))

(defn sev-at-psec [depth psec ds]
  (let [rng (get ds depth)]
    (if (nil? rng)
      0
      (if (zero? (nth (cycle (concat (range (dec rng)) (butlast (reverse (range rng))))) psec))
        (* depth rng)
        0))))

(defn run-through [firewall]
  (loop [cnt 0
         sev 0]
    (if (> cnt (apply max (keys firewall)))
      sev
      (recur (inc cnt) (+ sev (sev-at-psec cnt cnt firewall))))))

;; firewall contains a hash-map of depth and ranges
;; {0 14 50 24}
(def firewall
  (apply hash-map
         (map #(-> % str/trim Integer/parseInt)
         (str/split
           (slurp "resources/day13/firewall.txt")
           #"[\n:]"))))

(println "Day 13, Part 1: " (run-through firewall))
