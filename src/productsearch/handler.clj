(ns productsearch.handler
  (:require [liberator.core :refer [resource defresource]]
            [compojure.core :refer [defroutes ANY]]
            [ring.middleware.params :refer [wrap-params]]
            [productsearch.db.productdatasource :as ds]))

(defresource findProductById
  [id]
  :available-media-types ["text/plain"]
  ;:handle-ok (ds/findById id)
  :handle-ok (fn [_] (format "The product id we are searching on is %s" id))
  )

(defresource findCollectionCount
  [collection]
  :available-media-types ["text/plain"]
  :handle-ok (fn [_] (format "You are getting the count of products of the %s" collection)))

(defroutes app-routes
  (ANY "/" [] "Hello World")
  (ANY "/shop/product/:id" [id] (findProductById id))
  (ANY "/shop/products/count/:collection" [collection] (findCollectionCount collection)))

(def app
  (-> app-routes
      wrap-params))
