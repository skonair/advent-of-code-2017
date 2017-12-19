(ns clj-adventofcode.day3-test
  (:require [clojure.test :refer :all]
            [clj-adventofcode.day3 :refer :all]))

;; For example, the first few squares are allocated like this:
;;
;; 17  16  15  14  13
;; 18   5   4   3  12
;; 19   6   1   2  11
;; 20   7   8   9  10
;; 21  22  23---> ...

(def spiral-test {[0 0] 1 [1 0] 2 [1 1] 3 [0 1] 4 [-1 1] 5 [-1 0] 6
                  [-1 -1] 7 [0 -1] 8 [1 -1] 9})

(deftest spiral-test-part-1
  (testing "Data from square 1 is carried 0 steps, since it's at the access port."
    (is (= 0 (spiral-steps 1))))
  (testing "Data from square 12 is carried 3 steps, such as: down, left, left."
    (is (= 3 (spiral-steps 12))))
  (testing "Data from square 23 is carried only 2 steps: up twice."
    (is (= 2 (spiral-steps 23))))
  (testing "Data from square 1024 must be carried 31 steps."
    (is (= 31 (spiral-steps 1024)))))

(deftest neighbour-count-test
  (testing "neighbour-count empty is in (0 0) is 44"
    (is (= 44 (neighbour-count spiral-test [0 0])))))

(deftest spiral-test-part-2
  (testing "Square 1 starts with the value 1."
    (is (= 1 (spiral-steps-neighbour-count 1))))
  (testing "Square 2 has only one adjacent filled square (with value 1), so it also stores 1."
    (is (= 2 (spiral-steps-neighbour-count 2))))
  (testing "Square 3 has both of the above squares as neighbors and stores the sum of their values, 2."
    (is (= 4 (spiral-steps-neighbour-count 3))))
  (testing "Square 4 has all three of the aforementioned squares as neighbors and stores the sum of their values, 4."
    (is (= 4 (spiral-steps-neighbour-count 4))))
  (testing "Square 5 only has the first and fourth squares as neighbors, so it gets the value 5."
    (is (= 5 (spiral-steps-neighbour-count 5)))))
