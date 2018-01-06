(ns clj-adventofcode.day11-test
  (:require [clojure.test :refer :all]
            [clj-adventofcode.day11 :refer :all]))

(deftest path-steps-test
  (testing "ne,ne,ne is 3 steps away."
    (is (= 3 (steps "ne,ne,ne"))))
  (testing "ne,ne,sw,sw is 0 steps away (back where you started)."
    (is (= 0 (steps "ne,ne,sw,sw"))))
  (testing "ne,ne,s,s is 2 steps away (se,se)."
    (is (= 2 (steps "ne,ne,s,s"))))
  (testing "se,sw,se,sw,sw is 3 steps away (s,s,sw)."
    (is (= 3 (steps "se,sw,se,sw,sw")))))
