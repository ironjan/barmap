(ns barmap.model.places
  (:require [clojure.java.jdbc :as sql]
            [lobos.config :as config]))

(defn all []
  (into [] (sql/query db ["select * from places;"])))

(defn allViaType [type]
  (into [] (sql/query db ["select * from places where type_id = ?" type])))
