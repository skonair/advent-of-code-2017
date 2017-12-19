(ns clj-adventofcode.day2-test
  (:require [clojure.test :refer :all]
            [clj-adventofcode.day2 :refer :all]))

(deftest spreadsheet-test-part-1
  (testing "The first row's largest and smallest values are 9 and 1, and their difference is 8."
    (is (= 8 (row-diff "5 1 9 5"))))
  (testing "The second row's largest and smallest values are 7 and 3, and their difference is 4."
    (is (= 4 (row-diff "7 5 3"))))
  (testing "The third row's difference is 6."
    (is (= 6 (row-diff "2 4 6 8"))))
  (testing "In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18."
    (is (= 18 (spreadsheet-checksum-1 "5 1 9 5\n7 5 3\n2 4 6 8")))))

(deftest spreadsheet-test-part2
  (testing "In the first row, the only two numbers that evenly divide are 8 and 2; the result of this division is 4."
    (is (= 4 (row-dividebles "5 9 2 8"))))
  (testing "In the second row, the two numbers are 9 and 3; the result is 3."
    (is (= 3 (row-dividebles "9 4 7 3"))))
  (testing "In the third row, the result is 2."
    (is (= 2 (row-dividebles "3 8 6 5"))))
  (testing "In this example, the sum of the results would be 4 + 3 + 2 = 9."
    (is (= 9 (spreadsheet-checksum-2 "5 9 2 8\n9 4 7 3\n3 8 6 5")))))
