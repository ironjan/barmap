(defproject barmap "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://barmap.herokuapp.com"
  :license {:name "Apache 2.0: choose"
            :url "FIXME"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]
                 [ring/ring-jetty-adapter "1.2.2"]
                 [ring/ring-devel "1.2.2"]
                 [ring-basic-authentication "1.0.5"]
                 [environ "0.5.0"]
                 [com.cemerick/drawbridge "0.0.6"]
                 [org.clojure/java.jdbc "0.3.2"]
                 [postgresql "9.1-901.jdbc4"]
                 [hiccup "1.0.4"]
                 [ring-middleware-format "0.2.2"]
                 [lobos "1.0.0-beta3"]
                 [postgresql "9.3-1102.jdbc41"]
                 [heroku-database-url-to-jdbc "0.2.2"]]
  :main barmap.web
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.2.1"]]
  :hooks [environ.leiningen.hooks]
  :uberjar-name "barmap-standalone.jar"
  :profiles {:production {:env {:production true}}})
