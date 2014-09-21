(ns lobos.config
  (:use lobos.connectivity)
  (:require [heroku-database-url-to-jdbc.core :as heroku-url]))

(def db (or (heroku-url/jdbc-connection-string (System/getenv "DATABASE_URL"))
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "//localhost:5432/barmap"}))

(open-global db)