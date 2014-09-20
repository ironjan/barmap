(ns barmap.model.types
  (:require [clojure.java.jdbc :as sql]
            [lobos.config :as config]))


(defn all []
  (into [] (sql/query config/db ["select * from types;"])))

