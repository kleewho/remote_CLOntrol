(ns remote-clontrol.core.stb-type-test
  (:require [remote-clontrol.core.stb-type :refer :all]
            [clojure.test :refer :all]))

(deftest stb-is-keywordized-by-middleware

  (testing "d4a as stb type"
    (let [request {:params {:stb "d4a"}}
          result ((wrap-stb-type identity) request)]
      (is (= result :d4a)))))
