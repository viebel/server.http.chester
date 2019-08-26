(ns duct.server.http.chester
  (:require [chester.server :as chester]
            [duct.logger :as logger]
            [integrant.core :as ig]))

(defmethod ig/init-key :duct.server.http/chester [_ {:keys [logger] :as opts}]
  (let [handler (atom (delay (:handler opts)))
        logger  (atom logger)
        options (dissoc opts :handler :logger)]
    (logger/log @logger :report ::starting-server (select-keys opts [:port]))
    {:handler     handler
     :logger      logger
     :stop-server (chester/start {:server options} (fn [req] (@@handler req)))}))

(defmethod ig/halt-key! :duct.server.http/chester [_ {:keys [logger stop-server]}]
  (logger/log @logger :report ::stopping-server)
  (stop-server))

(defmethod ig/suspend-key! :duct.server.http/chester [_ {:keys [handler]}]
  (reset! handler (promise)))

(defmethod ig/resume-key :duct.server.http/chester [key opts old-opts old-impl]
  (if (= (dissoc opts :handler :logger) (dissoc old-opts :handler :logger))
    (do (deliver @(:handler old-impl) (:handler opts))
        (reset! (:logger old-impl) (:logger opts))
        old-impl)
    (do (ig/halt-key! key old-impl)
        (ig/init-key key opts))))
