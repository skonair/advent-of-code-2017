(ns clj-adventofcode.day6-test
  (:require [clojure.test :refer :all]
            [clj-adventofcode.day6 :refer :all]))

(deftest test-bank
  (testing "Redistribution after [0 2 7 0] is [2 4 1 2]"
    (is (= [2 4 1 2] (redistribute-banks [0 2 7 0]))))
  (testing "Redistribution after [2 4 1 2] is [3 1 2 3]"
    (is (= [3 1 2 3] (redistribute-banks [2 4 1 2]))))
  (testing "Redistribution after [3 1 2 3] is [0 2 3 4]"
    (is (= [0 2 3 4] (redistribute-banks [3 1 2 3]))))
  (testing "Redistribution after [0 2 3 4] is [1 3 4 1]"
    (is (= [1 3 4 1] (redistribute-banks [0 2 3 4]))))
  (testing "Redistribution after [1 3 4 1] is [2 4 1 2]"
    (is (= [2 4 1 2] (redistribute-banks [1 3 4 1]))))
  (testing "redistribution-cycles after [0 2 7 0] is 5"
    (is (= [5 4] (redistribution-cycles [0 2 7 0])))))
