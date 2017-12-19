(ns clj-adventofcode.day1-test
  (:require [clojure.test :refer :all]
            [clj-adventofcode.day1 :refer :all]))

(deftest captcha-test-part1
  (testing "1122 produces a sum of 3 (1 + 2) because the first digit (1) matches"
    (is (= 3 (captcha-day1-1 "1122"))))
  (testing "1111 produces 4 because each digit (all 1) matches the next."
    (is (= 4 (captcha-day1-1 "1111"))))
  (testing "1234 produces 0 because no digit matches the next."
    (is (= 0 (captcha-day1-1 "1234"))))
  (testing "91212129 produces 9 because the only digit that matches the next one is the last digit, 9."
    (is (= 9 (captcha-day1-1 "91212129")))))

(deftest captcha-test-part2
  (testing "1212 produces 6: the list contains 4 items, and all four digits match the digit 2 items ahead."
    (is (= 6 (captcha-day1-2 "1212"))))
  (testing "1221 produces 0, because every comparison is between a 1 and a 2."
    (is (= 0 (captcha-day1-2 "1221"))))
  (testing "123425 produces 4, because both 2s match each other, but no other digit has a match."
    (is (= 4 (captcha-day1-2 "123425"))))
  (testing "123123 produces 12."
    (is (= 12 (captcha-day1-2 "123123"))))
  (testing "12131415 produces 4."
    (is (= 4 (captcha-day1-2 "12131415")))))

