(ns productsearch.handler
  (:require [liberator.core :refer [resource defresource]]
            [compojure.core :refer [defroutes ANY]]
            [ring.middleware.params :refer [wrap-params]]
            [productsearch.db.productdatasource :as ds]))

(defresource findProductById
  [id]
  :available-media-types ["application/json"]
  :handle-ok (ds/findById id))

(defresource getCount
  []
  :available-media-types ["text/plain"]
  :handle-ok (ds/getCount))

(defresource searchProducts
  []
  :available-media-types ["text/plain"]
  :exists? (fn [ctx]
             (if-let [parameters
                      (get-in ctx [:request :params])]
                                      {:parameters parameters}))
  ;:handle-ok  (fn [ctx] (let [pm (get ctx :parameters)] (println (str (pm "type") (pm "query")))))
  :handle-ok  (fn [ctx] (let [paramValues (get ctx :parameters)]
                         (let [query (paramValues "query")
                               type (paramValues "type")]
                           (ds/findByAttributes query type))))
  :handle-not-found (fn [ctx] (format "invalid data %s" (get ctx :parameters))))

(defroutes app-routes
  (ANY "/" [] "Hello World")
  (ANY "/admin/product/:id" [id] (findProductById id))
  (ANY "/admin/product/all/count" [] (getCount))
  (ANY "/shop/search" [] (searchProducts)))

(def app
  (-> app-routes
      wrap-params))
