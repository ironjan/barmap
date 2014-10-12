(ns barmap.controllers.api
  (:use [ring.util.response])
  (:require [compojure.core :refer [defroutes context OPTIONS GET PUT POST DELETE
                                    ]]
            [ring.util.response :as ring]
            [barmap.model.places :as places]))


(defroutes routes
           (context "/api" []
                    (GET "/bars" [] (-> (response (places/all))))
                    (GET "/bars/:id" [id] (response (places/findById id)))
                    (OPTIONS "/" [] (-> (response {:version "0.1.0-SNAPSHOT"}) (header "Allow" "OPTIONS")))))