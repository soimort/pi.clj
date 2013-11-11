(ns pi.main)

(def rounding-mode BigDecimal/ROUND_HALF_EVEN)

(defn arccot [inverse-x scale]
  (let [inv-x (bigdec inverse-x)
        inv-x2 (bigdec (* inverse-x inverse-x))]
    (loop [i 1
           numer (.divide 1M inv-x scale rounding-mode)
           result numer]
      (let [numer-n (.divide numer inv-x2 scale rounding-mode)
            denom (+ 1 (* 2 i))
            term (.divide numer-n (bigdec denom) scale rounding-mode)
            result-n (if (= 0 (rem i 2))
                       (.add result term)
                       (.subtract result term))]
        (if (= 0 (.compareTo term 0M))
          result-n
          (recur (+ 1 i) numer-n result-n))))))

(defn pi [digits]
  (let [scale (+ 5 digits)
        arccot5 (arccot 5 scale)
        arccot239 (arccot 239 scale)
        pi (.multiply (bigdec 4)
                      (.subtract (.multiply (bigdec 4) arccot5)
                                 arccot239))]
    (.setScale pi digits BigDecimal/ROUND_HALF_UP)))

(defn -main
  "Calculates Pi using Machin's Formula."
  ([]
     (println "Usage: lein run <num-of-digits>"))
  ([&args]
     (printf "%s\n"
             (pi (Integer. (first *command-line-args*))))))
