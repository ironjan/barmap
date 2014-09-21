(ns lobos.config
  (:use lobos.connectivity))

(def db (or (System/getenv "DATABASE_URL")
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "//localhost:5432/barmap"}))

(open-global db)