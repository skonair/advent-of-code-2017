(ns clj-adventofcode.day10-test
  (:require [clojure.test :refer :all]
            [clj-adventofcode.day10 :refer :all]))

(deftest dense-hash-test
  (testing "The empty string becomes a2582a3a0e66e6e86e3812dcb672a272."
    (is (= "a2582a3a0e66e6e86e3812dcb672a272" (dense-hash standard-list ""))))
  (testing "AoC 2017 becomes 33efeb34ea91902bb2f59c9920caa6cd."
    (is (= "33efeb34ea91902bb2f59c9920caa6cd" (dense-hash standard-list "AoC 2017"))))
  (testing "1,2,3 becomes 3efbe78a8d82f29979031a4aa0b16a9d."
    (is (= "3efbe78a8d82f29979031a4aa0b16a9d" (dense-hash standard-list "1,2,3"))))
  (testing "1,2,4 becomes 63960835bcdc130f0b66d7ff4f6a5a8e."
    (is (= "63960835bcdc130f0b66d7ff4f6a5a8e" (dense-hash standard-list "1,2,4")))))
