(defproject viebel/server.http.chester "0.1.2"
  :description "Integrant methods for running a Chester web server"
  :url "https://github.com/viebel/server.http.chester"
  :license {:name "EPL-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[com.cycognito/chester "0.2.0" :scope "provided"]
                 [duct/core "0.7.0" :scope "provided"]
                 [duct/logger "0.3.0" :scope "provided"]
                 [integrant "0.7.0" :scope "provided"]
                 [org.clojure/clojure "1.10.0" :scope "provided"]]
  :repositories [["snapshots" {:url      "https://maven.cyco.fun/snapshots"
                               :username :env/cyco_maven_username
                               :password :env/cyco_maven_password}]
                 ["releases" {:url      "https://maven.cyco.fun/releases"
                              :username :env/cyco_maven_username
                              :password :env/cyco_maven_password}]])
