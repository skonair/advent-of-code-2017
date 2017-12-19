(ns clj-adventofcode.day7
  (:require [clojure.string :as str]))

;; pbga (66)
;; xhth (57)
;; ebii (61)
;; havc (66)
;; ktlj (57)
;; fwft (72) -> ktlj, cntj, xhth
;; qoyq (66)
;; padx (45) -> pbga, havc, qoyq
;; tknk (41) -> ugml, padx, fwft
;; jptl (61)
;; ugml (68) -> gyxo, ebii, jptl
;; gyxo (61)
;; cntj (57)

;;                 gyxo
;;               /
;;          ugml - ebii
;;        /      \
;;       |         jptl
;;       |
;;       |         pbga
;;      /        /
;; tknk --- padx - havc
;;      \        \
;;       |         qoyq
;;       |
;;       |         ktlj
;;        \      /
;;          fwft - cntj
;;               \
;;                 xhth


(defn parse-line
  [line]
  (let [[name value & children] (re-seq #"\w+" line)]
    {:name name :value value :children children} ))

(def lines-of-file
  (str/split-lines
    (slurp "resources/day7/programs.txt")))

(defn in?
  "true if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))

(defn get-parent
  [programs program-name]
  (filter #(in? (:children %) program-name) programs))

(defn get-root-program
  [programs]
  (filter #(empty? (get-parent programs (:name %))) programs))

(defn get-program-by-name
  [programs program-name]
  (first (filter #(= (:name %) program-name) programs)))

(defn get-children
  [programs program]
  ;;  (println "get-children: " program)
  (let [children-name (:children program)]
    (if (nil? children-name)
    nil
    (map #(get-program-by-name programs %) children-name))))

(defn weight
  [programs program]
  (let [children (get-children programs program)]
    (if (nil? children)
      (Integer/parseInt (:value program))
      (let [v1 (Integer/parseInt (:value program))
            ws (map #(weight programs %) children)]
        (reduce + v1 ws)))))

(defn children-balanced?
  [programs program]
  (let [children (get-children programs program)]
    (if (nil? children)
      true
      (apply = (map #(weight programs %) children)))))

(defn unbalanced
  [programs]
  (filter #(not (children-balanced? programs %))  programs))

(def programs (map parse-line lines-of-file))

(println "Day 7, Part 1: " (get-root-program programs))
(println "Day 7, Part 2: " (unbalanced programs))
;; solution is 646 for rfkvap