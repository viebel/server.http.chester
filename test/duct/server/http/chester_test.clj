(ns duct.server.http.chester-test
  (:import java.net.ConnectException)
  (:require [clj-http.client :as http]
            [clojure.test :refer [deftest is testing]]
            [duct.core :as duct]
            [integrant.core :as ig]))

;; inspired from https://github.com/duct-framework/server.http.jetty/blob/master/test/duct/server/http/jetty_test.clj

(duct/load-hierarchy)

(deftest key-test
  (is (isa? :duct.server.http/chester :duct.server/http)))

(deftest init-and-halt-test
  (let [response {:status 200 :headers {} :body "test"}
        handler  (constantly response)
        config   {:duct.server.http/chester {:port 3400, :handler handler}}]

    (testing "server starts"
      (let [system (ig/init config)]
        (try
          (let [response (http/get "http://127.0.0.1:3400/aa")]
            (is (:status response))
            (is (= "test" (:body response))))
          (finally
            (ig/halt! system)))))

    (testing "server stops"
      (is (thrown? ConnectException (http/get "http://127.0.0.1:3400/"))))

    (testing "halt is idempotent"
      (let [system (ig/init config)]
        (ig/halt! system)
        (ig/halt! system)
        (is (fn? (-> system :duct.server.http/chester :stop-server)))))))
