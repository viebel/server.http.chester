(defproject viebel/server.http.chester "0.1.0"
  :description "Integrant methods for running a Chester web server"
  :url "https://github.com/viebel/server.htttp.chester"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[com.cycognito/chester "0.2.0"]
                 [duct/core "0.7.0"]
                 [duct/logger "0.3.0"]
                 [integrant "0.7.0"]
                 [org.clojure/clojure "1.10.0"]]
  :repositories [["snapshots" {:url "https://maven.cyco.fun/snapshots"
                               :username :env/cyco_maven_username
                               :password :env/cyco_maven_password}]
                 ["releases" {:url "https://maven.cyco.fun/releases"
                              :username :env/cyco_maven_username
                              :password _:env/cyco_maven_password}]])
