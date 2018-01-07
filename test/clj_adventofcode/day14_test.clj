(ns clj-adventofcode.day14-test
  (:require [clojure.test :refer :all]
            [clj-adventofcode.day14 :refer :all]))

(def test-input "flqrgnkx")

(deftest squares-used-test
  (testing "In this example, 8108 squares are used across the entire 128x128 grid."
    (is (= 8108 (squares-used test-input)))))
