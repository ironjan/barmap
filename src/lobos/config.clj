(ns lobos.config
  (:use [lobos.connectivity]
        [heroku-database-url-to-jdbc.core]))

(def localDb
  {:classname   "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname     "//localhost:5432/barmap"})


(defn db []
  (if-let
      [url (System/getenv "DATABASE_URL")]
    (korma-connection-map url)
    localDb))

(defn init []
  (open-global (db)))