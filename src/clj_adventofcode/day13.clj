(ns clj-adventofcode.day13
  (:require [clojure.string :as str]))

(defn caught-at? [depth psec ds]
  (let [rng (get ds depth)]
    (if (nil? rng)
      false
      (let [ofs (mod psec (* 2 (dec rng)))
            ccl (cycle (concat (range (dec rng)) (butlast (reverse (range rng)))))]
      (zero? (nth ccl ofs))))))

(defn sev-at-psec [depth psec ds]
    (if (caught-at? depth psec ds)
      (* depth (get ds depth))
      0))

(defn run-through-with-delay [frwl dly]
  (loop [cnt 0
         sev 0]
    (if (> cnt (apply max (keys frwl)))
      sev
      (recur (inc cnt) (+ sev (sev-at-psec cnt (+ cnt dly) frwl))))))

(defn run-through [firewall]
  (run-through-with-delay firewall 0))

(defn caught-with-delay? [frwl dly]
  (let [maxkeys (apply max (keys frwl))]
  (loop [cnt 0
         cgt false]
    (if (or cgt (> cnt maxkeys))
      cgt
      (recur (inc cnt) (caught-at? cnt (+ cnt dly) frwl))))))

(defn uncaught-delay [frwl]
  (loop [dly 0]
    (if (not (caught-with-delay? frwl dly))
      dly
      (recur (inc dly)))))

(def firewall
  (apply hash-map
         (map #(-> % str/trim Integer/parseInt)
         (str/split
           (slurp "resources/day13/firewall.txt")
           #"[\n:]"))))

(println "Day 13, Part 1: " (run-through firewall))
;; 788
(println "Day 13, Part 2: " (uncaught-delay firewall))
;; 3905748
