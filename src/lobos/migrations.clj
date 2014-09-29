(ns lobos.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema
               config helpers))
  (:require [clojure.java.jdbc :as sql]))


(defmigration add-places-table
              (up [] (create (tbl :types (varchar :name 20 :unique)))
                  (create (tbl :places (varchar :name 50 :unique)
                                  (varchar :description 500)
                                  (varchar :special 150)
                                  (double :lat)
                                  (double :lon)
                                  (varchar :url 50)
                                  (varchar :tel 20)
                                  (boolean :freifunk )
                                  (check :name (> (length :name) 1))
                                  (refer-to :types))))
              (down [] (drop (table :places) :cascade)
                    (drop (table :types) :cascade)))

(defmigration add-basic-types
              (up [] (sql/insert! (lobos.config/db) :types
                                  {:id 1 :name "Unknown"}
                                  {:name "Bar"}
                                  {:name "Pub"}
                                  {:name "Take Away"} ))
              (down [] (sql/delete! (lobos.config/db) :types [])))

