(ns productsearch.db.productdatasource
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.operators :refer :all]
            [clojure.data.json :as json]))

(defn- write-json-mongodb-objectid [x out]
  (json/write (str x) out))

(extend org.bson.types.ObjectId json/JSONWriter
        {:-write write-json-mongodb-objectid})

(defn parse-int
  [s]
  (Integer. (re-find #"\d+" s)))

(defn findById
  [id]
  (let [conn (mg/connect {:host "ds031751.mongolab.com" :port 31751})
        db (mg/get-db conn "product")
        u "clam"
        p (.toCharArray "password")
        coll "apple"
        auth (mg/authenticate db u p)]
    (let [data (mc/find-maps db coll {:id (parse-int id)})]
;      (println data)
      (json/write-str data))))

(defn getCount
  []
  (let [conn (mg/connect {:host "ds031751.mongolab.com" :port 31751})
        db (mg/get-db conn "product")
        u "clam"
        p (.toCharArray "password")
        coll "apple"
        auth (mg/authenticate db u p)]
    (let [data (mc/count db coll)]
;      (println data)
      (json/write-str data))))

(defn findByAttributes
  [query type]
  (let [conn (mg/connect {:host "ds031751.mongolab.com" :port 31751})
        db (mg/get-db conn "product")
        u "clam"
        p (.toCharArray "password")
        coll "apple"
        auth (mg/authenticate db u p)]
    (let [data (mc/find-maps db coll {:title {$regex query $options "i"}, :variants {$elemMatch {:title "Green"}}})]
;      (println data)
      (json/write-str data))))

