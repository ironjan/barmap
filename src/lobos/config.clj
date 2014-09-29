(ns lobos.config
  (:use [lobos.connectivity]
        [heroku-database-url-to-jdbc.core]))

(defn db []
;; FIXME korma-connection-map throws NPE if DATABASE_URL is not set
  (or (korma-connection-map (System/getenv "DATABASE_URL"))
              {:classname   "org.postgresql.Driver"
               :subprotocol "postgresql"
               :subname     "//localhost:5432/barmap"}))

(defn init []
  (open-global (db)))