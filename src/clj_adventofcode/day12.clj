(ns clj-adventofcode.day12
  (:require [clojure.string :as str]))

(defn in? [coll elm]
  (some #(= elm %) coll))

(defn get-conns [id cs akku]
    (filter #(not (or (= id %) (in? akku %))) (get cs id)))

(defn groups [id cs]
  (loop [i id
         ids []
         akku []]
    (if (nil? i)
      (set akku)
      (let [chd (get-conns i cs akku)
            new-ids (concat ids chd)]
        (recur (first new-ids) (rest new-ids) (conj akku i))))))

(defn independent-groups [cs]
  (loop [lst cs
         cnt 0]
    (if (empty? lst)
      cnt
      (recur
        (apply dissoc lst (set (groups (first (first lst)) cs)))
        (inc cnt)))))

(def connections-by-file
  (apply merge
    (map #(hash-map (first %) (rest %))
        (map #(str/split % #"\D+")
        (-> "resources/day12/pipes.txt"
             slurp
             str/split-lines)))))

(println "Day 12, Part 1: " (count (groups "0" connections-by-file)))
;; 288
(println "Day 12, Part 1: " (independent-groups connections-by-file))
;; 211
