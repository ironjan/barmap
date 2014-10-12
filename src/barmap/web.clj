(ns barmap.web
  (:use [compojure.core]
        [ring.util.response]
        [ring.middleware.format-response]
        [hiccup.core])
  (:require
    [compojure.handler :as handler]
    [compojure.route :as route]
    [clojure.java.io :as io]
    [ring.middleware.stacktrace :as trace]
    [ring.middleware.session :as session]
    [ring.middleware.session.cookie :as cookie]
    [ring.adapter.jetty :as jetty]
    [ring.middleware.basic-authentication :as basic]
    [cemerick.drawbridge :as drawbridge]
    [barmap.controllers.api :as apiController]
    [environ.core :refer [env]]
    [barmap.model.places :as places]))

(defn- authenticated? [user pass]
  ;; TODO: heroku config:add REPL_USER=[...] REPL_PASSWORD=[...]
  (= [user pass] [(env :repl-user false) (env :repl-password false)]))

(def ^:private drawbridge
  (-> (drawbridge/ring-handler)
      (session/wrap-session)
      (basic/wrap-basic-authentication authenticated?)))


(defn wrap-error-page [handler]
  (fn [req]
    (try (handler req)
         (catch Exception e
           {:status  500
            :headers {"Content-Type" "text/html"}
            :body    (slurp (io/resource "500.html"))}))))

(defroutes app
           apiController/routes
           (ANY "/repl" {:as req}
                (drawbridge req))
           (GET "/bars" []
                (html
                  [:html5
                   [:body [:div#content
                           [:ul#locationList
                            (for [bar (places/all)]
                              [:li (:name bar)])]]]]))
           (GET "/bars/:id" [id]
                (html
                  [:html5
                   [:body [:div#content
                           [:h1 (:name (places/findById id))]
                           ]]]))
           (GET "/" [] (html [:html5 [:body [:div#content [:p "A simple landing page"]]]]))
           (ANY "*" []
                (route/not-found (slurp (io/resource "404.html")))))

(defn wrap-app [app]
  ;; TODO: heroku config:add SESSION_SECRET=$RANDOM_16_CHARS
  (let [store (cookie/cookie-store {:key (env :session-secret)})]
    (-> app
        ((if (env :production)
           wrap-error-page
           trace/wrap-stacktrace))
        (wrap-restful-response)
        (handler/site {:session {:store store}}))))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (wrap-app #'app) {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))
