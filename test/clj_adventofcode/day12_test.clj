(ns clj-adventofcode.day12-test
  (:require [clojure.test :refer :all]
            [clj-adventofcode.day12 :refer :all]))

(def test-connections
  {"0" '("2")
   "1" '("1")
   "2" '("0" "3" "4")
   "3" '("2" "4")
   "4" '("2" "3" "6")
   "5" '("6")
   "6" '("4" "5")})

(deftest pipes-test
  (testing "Therefore, a total of 6 programs are in this group; all but program 1, which has a pipe that connects it to itself."
    (is (= 6 (count (groups "0" test-connections))))))
