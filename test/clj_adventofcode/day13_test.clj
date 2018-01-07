(ns clj-adventofcode.day13-test
  (:require [clojure.test :refer :all]
            [clj-adventofcode.day13 :refer :all]))

(def test-firewall {0 3 1 2 4 4 6 4})

(deftest run-through-test
  (testing "In the example above, the trip severity is 0*3 + 6*4 = 24."
    (is (= 24 (run-through test-firewall)))))

(deftest uncaught-delay-test
  (testing "You would need to delay to get through safely is 10."
    (is (= 10 (uncaught-delay test-firewall)))))
