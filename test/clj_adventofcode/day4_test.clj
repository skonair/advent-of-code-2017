(ns clj-adventofcode.day4-test
  (:require [clojure.test :refer :all]
            [clj-adventofcode.day4 :refer :all]))

(deftest passphrase-test-part1
  (testing "aa bb cc dd ee is valid."
    (is (passphrase-valid? "aa bb cc dd ee")))
  (testing "aa bb cc dd aa is not valid - the word aa appears more than once."
    (is (not (passphrase-valid? "aa bb cc dd aa"))))
  (testing "aa bb cc dd aaa is valid - aa and aaa count as different words."
    (is (passphrase-valid? "aa bb cc dd aaa"))))

(deftest passphrase-test-part2
  (testing "abcde fghij is a valid passphrase."
    (is (passphrase-ext-valid? "abcde fghij")))
  (testing "abcde xyz ecdab is not valid - the letters from the third word can be rearranged to form the first word."
    (is (not (passphrase-ext-valid? "abcde xyz ecdab"))))
  (testing "a ab abc abd abf abj is a valid passphrase, because all letters need to be used when forming another word."
    (is (passphrase-ext-valid? "a ab abc abd abf abj")))
  (testing "iiii oiii ooii oooi oooo is valid."
    (is (passphrase-ext-valid? "iiii oiii ooii oooi oooo")))
  (testing "oiii ioii iioi iiio is not valid - any of these words can be rearranged to form any other word."
    (is (not (passphrase-ext-valid? "oiii ioii iioi iiio")))))
