(ns productsearch.productdatasource
  (:require [monger.core :as mg]
            [monnger.collection :as mc]))

(defn findById
  [id]
  (let [conn (mg/connect {:host "ds031751.mongolab.com" :port 31751})
        db (mg/get-db conn "product")
                      u "clam"
                      p (.toCharArray "password")
        coll "apple"]
    (mg/authenticate db u p)
  (mc/find db "product" {:id 632910392})))

