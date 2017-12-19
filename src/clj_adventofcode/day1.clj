(ns clj-adventofcode.day1)

(defn str-to-numericlist
  "converts a string to a numeric list"
  [s]
  map #(Character/getNumericValue %) s)

(defn compare-tuple
  "compares 2 numbers in the tuple and returns the value if they are the same, otherwise it returns 0"
  [x]
  (let [[a b] x]
    (if (= a b)
      (Character/getNumericValue a)
      0)))

(defn compute-captcha
  "Sums all compare-tuples of a list of tuples with the given stepwidth."
  [nums step]
  (reduce + 0
          (map compare-tuple
               (partition 2 step nums))))

(defn captcha-day1-1
  "Computes the AoC day 1.1 captcha."
  [x]
  (let [cycle (str x (first x))
        nums (str-to-numericlist cycle)]
    (compute-captcha nums 1)))


(defn captcha-day1-2
  "Computes the AoC day 1.2 captcha."
  [x]
  (let [nums (str-to-numericlist x)
        halfway (/ (count x) 2)
        halflist (split-at halfway nums)
        tuple-list (map vector (flatten(first halflist)) (flatten (last halflist)))]
    (* 2
      (compute-captcha
        (flatten tuple-list) 2))))

(def captcha (slurp "resources/day1/captcha.txt"))

(println "Day 1, Part 1: " (captcha-day1-1 captcha))
(println "Day 1, Part 2: " (captcha-day1-2 captcha))

