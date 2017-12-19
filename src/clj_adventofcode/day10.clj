(ns clj-adventofcode.day10)

(defn shift-right
  [ls n]
  (loop [shift-ls ls
         cnt n]
    (if (zero? cnt)
      shift-ls
      (let [tail (last shift-ls)
            head (take (dec (count ls)) shift-ls)]
        (recur (conj head tail) (dec cnt))))))

(defn shift-left
  [ls n]
  (loop [shift-ls (vec ls)
         cnt n]
    (if (zero? cnt)
      (lazy-seq shift-ls)
      (let [tail (first shift-ls)
            head (vec (drop 1 shift-ls))]
        (recur (conj head tail) (dec cnt))))))

(defn reverse-section
  [l offset length]
  (let [shifted-ls (shift-left l offset)
        sublist (take length shifted-ls)
        restlist (drop length shifted-ls)
        reversed-sublist (reverse sublist)
        new-shifted-list (concat reversed-sublist restlist)
        new-list (shift-right new-shifted-list offset)]
    new-list))

(defn step
  [ls current-pos skip-size length]
  (let [steps (+ skip-size length)
        new-ls (reverse-section ls current-pos length)
        new-pos (mod (+ current-pos steps) (count ls))
        new-skip-size (inc skip-size)]
    [new-ls new-pos new-skip-size]))

(defn knot-hash
  [clist input]
  (loop [[ls pos skip-size] [clist 0 0]
         [i & is] input]
    (if (nil? i)
      (reduce * 1 (take 2 ls))
      (recur (step ls pos skip-size i) is))))

(def standard-list (range 256))

(def puzzle-input '(46 41 212 83 1 255 157 65 139 52 39 254 2 86 0 204))
;; 46,41,212,83,1,255,157,65,139,52,39,254,2,86,0,204

(println "Day 10, Part 1: " (knot-hash standard-list puzzle-input))
;; 52070