(ns clj-adventofcode.day3
  (:require [clojure.string :as str]))

;; Each square on the grid is allocated in a spiral pattern starting
;; at a location marked 1 and then counting up while spiraling outward.
;; For example, the first few squares are allocated like this:
;;
;; 17  16  15  14  13
;; 18   5   4   3  12
;; 19   6   1   2  11
;; 20   7   8   9  10
;; 21  22  23---> ...
;;
;; We model a spiral as a HashMap: Key is the coordinate and value
;; the given value.

(def check-hash {:right [1 0] :up [0 1] :left [-1 0] :down [0 -1]})

(def move-fail-hash {:right [0 -1] :up [1 0] :left [0 1] :down [-1 0]})

(defn next-direction
  "returns the next direction"
  [direction]
  (cond
    (= direction :right) :up
    (= direction :up) :left
    (= direction :left) :down
    (= direction :down) :right))

(defn add-coords
  [pos d]
  (let [[lx, ly] pos
        [dx, dy] d]
    [(+ lx dx) (+ ly dy)]))

(defn next-coord-empty?
  "Checks whether the coord is not part of the spiral."
  [spiral last-coord move-coord-ok]
  (let [next-ok (add-coords last-coord move-coord-ok)]
    (nil? (spiral next-ok))))

(defn next-coord
  "Checks whether the coord is not part of the spiral."
  [nce? last-coord move-coord-ok move-coord-fail]
  (let [next-ok (add-coords last-coord move-coord-ok)
        next-fail (add-coords last-coord move-coord-fail)]
    (if nce? next-ok next-fail)))

(defn next-spiral-entry
  "Computes the next entry plus next direction for a given spiral
   and a given direction."
  [spiral last-coord direction]
  (let [delta-ok (check-hash direction)
        delta-fail (move-fail-hash direction)
        nce? (next-coord-empty? spiral last-coord delta-ok)
        nc (next-coord nce? last-coord delta-ok delta-fail)
        dir (if nce? (next-direction direction) direction)]
      [nc dir]))

(defn spiral-steps
  "Computes the steps from the center for a value in the spiral memory."
  [v]
  (loop [c 1
         last-coord [0 0]
         spiral {[0 0] 1}
         direction :right]
    (if (= v c)
      (let [[x y] last-coord]
        (+ (Math/abs x) (Math/abs y)))
      (let [[nc nd] (next-spiral-entry spiral last-coord direction)]
        (recur (inc c) nc (assoc spiral nc (inc c)) nd)))))

(defn valOrZero
  [spiral c]
  (let [v (spiral c)]
    (if (nil? v) 0 v)))

(defn neighbour-count
  [spiral coord]
  (let [[x y] coord
        coords [[(dec x) (inc y)]
                [x (inc y)]
                [(inc x) (inc y)]
                [(dec x) y]
                [(inc x) y]
                [(dec x) (dec y)]
                [x (dec y)]
                [(inc x) (dec y)]]
        values (map (partial valOrZero spiral) coords)]
    (reduce + 0 values)))

(defn spiral-steps-neighbour-count
  "Computes the steps from the center for a value in the spiral memory."
  [v]
  (loop [c 1
         last-coord [0 0]
         spiral {[0 0] 1}
         direction :right
         last-neighbour-count 1]
    (if (>= last-neighbour-count v)
      last-neighbour-count
      (let [[nc nd] (next-spiral-entry spiral last-coord direction)
            nnc (neighbour-count spiral nc)]
        (recur (inc c) nc (assoc spiral nc nnc) nd nnc)))))


(println "Day 3, Part 1: " (spiral-steps 347991))
;; 480
(println "Day 3, Part 2: " (spiral-steps-neighbour-count 347991))
;; 349975
