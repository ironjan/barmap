(ns lobos.config
  (:use lobos.connectivity)
  (:require [heroku-database-url-to-jdbc.core :as heroku-url]))

(def db
  {:classname   "org.postgresql.Driver"
   :subprotocol "postgresql"
   :dbname      (or (System/getenv "DB_DATABASE") "barmap")
   :host        (or (System/getenv "DB_HOST") "localhost")
   :port        (or (System/getenv "DB_PORT") 5432)
   :user        (or (System/getenv "DB_USER") "")
   :password    (or (System/getenv "DB_PASSWORD") "")
   :sslfactory "org.postgresql.ssl.NonValidatingFactory"
   :ssl true})

(open-global db)