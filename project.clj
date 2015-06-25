(defproject ecommerce_product_search "0.1.0-SNAPSHOT"
  :description "RESTful product search service"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.4"]
                 [liberator "0.13"]
                 [com.novemberain/monger "3.0.0-rc1"]
                 [ring/ring-core "1.3.2"]]
  :plugins [[lein-ring "0.9.6"]]
  :ring {:handler ecommerce-product-search.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
