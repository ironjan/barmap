(ns barmap.controllers.api
  (:use [ring.util.response])
  (:require [compojure.core :refer [defroutes context OPTIONS]]
            [ring.util.response :as ring]))


(defroutes routes
           (context "/api" []
                    (OPTIONS "/" [] (-> (response {:version "0.1.0-SNAPSHOT"}) (header "Allow" "OPTIONS")))))