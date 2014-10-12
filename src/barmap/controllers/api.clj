(ns barmap.controllers.api
  (:use [ring.util.response])
  (:require [compojure.core :refer [defroutes context OPTIONS GET PUT POST DELETE]]
            [clojure.data.json :as json]
            [ring.util.response :as ring]
            [barmap.model.places :as places]))


(defroutes routes
           (context "/api" []
                    (GET "/bars" [] (-> (response (places/all))))
                    (GET "/bars/:id" [id] (response (places/findById id)))
                    (POST "/bars" {body :body} (response (places/create! (json/read-str (slurp body)))))
                    (DELETE "/bars/:id" [id] (response (places/delete! id)))
                    (OPTIONS "/" [] (-> (response {:version "0.1.0-SNAPSHOT"}) (header "Allow" "OPTIONS")))))