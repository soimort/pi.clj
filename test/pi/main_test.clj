(ns pi.main-test
  (:require [clojure.test :refer :all]
            [pi.main :refer :all]))

(deftest arccot-test
  (testing "ArcCot 5"
    (is (= (arccot 5 4) 0.1974M))
    (is (= (arccot 5 10) 0.1973955598M))))

(deftest pi-test
  (testing "Pi"
    (is (= (pi 2) 3.14M))
    (is (= (pi 4) 3.1416M))))
