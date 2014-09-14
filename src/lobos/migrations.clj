(ns lobos.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema
               config helpers)))


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
