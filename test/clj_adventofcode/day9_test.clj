(ns clj-adventofcode.day9-test
  (:require [clojure.test :refer :all]
            [clj-adventofcode.day9 :refer :all]))

(deftest test-score
  (testing "{}, score of 1."
    (is (= 1 (score "{}"))))
  (testing "{{{}}}, score of 1 + 2 + 3 = 6."
    (is (= 6 (score "{{{}}}"))))
  (testing "{{},{}}, score of 1 + 2 + 2 = 5."
    (is (= 5 (score "{{},{}}"))))
  (testing "{{{},{},{{}}}}, score of 1 + 2 + 3 + 3 + 3 + 4 = 16."
    (is (= 16 (score "{{{},{},{{}}}}"))))
  (testing "{<a>,<a>,<a>,<a>}, score of 1."
    (is (= 1 (score "{<a>,<a>,<a>,<a>}"))))
  (testing "{{<ab>},{<ab>},{<ab>},{<ab>}}, score of 1 + 2 + 2 + 2 + 2 = 9."
    (is (= 9 (score "{{<ab>},{<ab>},{<ab>},{<ab>}}"))))
  (testing "{{<!!>},{<!!>},{<!!>},{<!!>}}, score of 1 + 2 + 2 + 2 + 2 = 9."
    (is (= 9 (score "{{<!!>},{<!!>},{<!!>},{<!!>}}"))))
  (testing "{{<a!>},{<a!>},{<a!>},{<ab>}}, score of 1 + 2 = 3."
    (is (= 3 (score "{{<a!>},{<a!>},{<a!>},{<ab>}}")))))

(deftest test-removed-garbage
  (testing "<>, 0 characters."
  (is (= 0 (removed-garbage "<>"))))
  (testing "<random characters>, 17 characters."
  (is (= 17 (removed-garbage "<random characters>"))))
  (testing "<<<<>, 3 characters."
  (is (= 3 (removed-garbage "<<<<>"))))
  (testing "<{!>}>, 2 characters."
  (is (= 2 (removed-garbage "<{!>}>"))))
  (testing "<!!>, 0 characters."
  (is (= 0 (removed-garbage "<!!>"))))
  (testing "<!!!>>, 0 characters."
  (is (= 0 (removed-garbage "<!!!>>"))))
  (testing "<{o\"i!a,<{i<a>, 10 characters."
  (is (= 10 (removed-garbage "<{o\"i!a,<{i<a>")))))
