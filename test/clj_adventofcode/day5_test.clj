(ns clj-adventofcode.day5-test
  (:require [clojure.test :refer :all]
            [clj-adventofcode.day5 :refer :all]))

(deftest escaped-test
  (testing "[0 1 2] at pos 3"
    (is (escaped? [0 1 2] 3))))

(deftest next-step-test
  (testing "From (0) 3  0  1 -3 to (1) 3  0  1 -3"
    (is (= [[1 3 0 1 -3] 0] (next-step [0 3 0 1 -3] 0))))
  (testing "From (1) 3  0  1 -3 to 2 (3)  0  1 -3"
    (is (= [[2 3 0 1 -3] 1] (next-step [1 3 0 1 -3] 0))))
  (testing "From 2 (3)  0  1 -3 to 2 4  0  1 (-3)"
    (is (= [[2 4 0 1 -3] 4] (next-step [2 3 0 1 -3] 1))))
  (testing "From 2 4  0  1 (-3) to 2 (4)  0  1 -2"
    (is (= [[2 4 0 1 -2] 1] (next-step [2 4 0 1 -3] 4))))
  (testing "From 2 (4)  0  1 -2 to 2 5  0 1 -2"
    (is (= [[2 5 0 1 -2] 5] (next-step [2 4 0 1 -2] 1)))))

(deftest exit-step-test
  (testing "From (0) 3  0  1 -3 it takes 5 steps to reach exit."
    (is (= 5 (exit-steps [0 3 0 1 -3])))))

(deftest ext-step-strange-test
  (testing "From (0) 3  0  1 -3 it takes 10 steps to reach exit."
    (is (= 10 (exit-steps-strange [0 3 0 1 -3])))))
