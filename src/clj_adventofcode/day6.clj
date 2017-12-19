(ns clj-adventofcode.day6)

(defn next-idx
  [banks idx]
  (if (>= (inc idx) (count banks))
    0
    (inc idx)))

(defn redistribute-banks
  "Redistributes the banks based on the required algo."
  [banks]
  (let [bank-max (apply max banks)
        bank-max-idx (.indexOf banks bank-max)
        banks-start (assoc banks bank-max-idx 0)]
    (loop [idx bank-max-idx
           cnt bank-max
           banks-cur banks-start]
      (let [idx-next (next-idx banks idx)]
        (if (zero? cnt)
          banks-cur
          (recur
            idx-next
            (dec cnt)
            (assoc banks-cur idx-next (inc (banks-cur idx-next)))))))))

(defn in?
  "true if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))

(defn redistribution-cycles
  "Counts the redistribution cycles until a redistribution occurs again."
  [banks]
  (loop [cur-banks banks
         akku []
         cnt 0]
    (if (in? akku cur-banks)
      [cnt (- cnt (.indexOf akku cur-banks))]
      (recur (redistribute-banks cur-banks) (conj akku cur-banks) (inc cnt)))))

(def init-config [2 8 8 5 4 2 3 1 5 5 1 2 15 13 5 14])

(println "Day 6, Part 1: " (redistribution-cycles init-config))