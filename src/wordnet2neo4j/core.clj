(ns wordnet2neo4j.core
  (:require [plaza.rdf.core :as rdf]
            [plaza.rdf.implementations.sesame :as sesame]
            [clojurewerkz.neocons.rest :as nr]
            [clojurewerkz.neocons.rest.nodes :as nn]
            [clojurewerkz.neocons.rest.relationships :as nrl]
            [clojurewerkz.neocons.rest.cypher :as cy])
  (:import (java.util.zip GZIPInputStream)
           (java.io File FileInputStream)))

(sesame/init-sesame-framework)
(nr/connect! "http://localhost:7474/db/data/")

(defn parse-file [f]
  (println "Parsing file " (.getAbsolutePath f) "...")
  (let [gzip (if (.endsWith (.getAbsolutePath f) ".ttl")
               (FileInputStream. f)
               (GZIPInputStream. (FileInputStream. f)))]
    (try
      (map #(map str %) (rdf/model-to-triples (rdf/document-to-model gzip :turtle)))
      (catch Exception e
        []))))

(defn node-id [node]
  (java.lang.Long/parseLong
    (clojure.string/replace
      (clojure.string/replace
        (re-find #"node.*.labels" (:labels node)) #"node/" "")
      #"/labels" "")))

(defn new-node-id [m]
  (let [new-node (nn/create m)]
    (:id new-node)))

(defn get-node [uri]
  (try
    (let [result-set (cy/tquery "START a = node:node_auto_index(name={name}) RETURN a;" {:name uri})]
      (if (empty? result-set)
        (nn/get (new-node-id {:name uri}))
        (nn/get (node-id (get (first result-set) "a")))))
    (catch Exception e
      ;; Keep trying
      (nn/get (new-node-id {:name uri})))))

(defn insert-triple [triple]
  (let [subject (get-node (first triple))
        predicate (second triple)
        object (get-node (nth triple 2))]
    (nrl/create subject object predicate)))

(defn insert-triple-set [tset]
  (dorun (map insert-triple tset)))

#_(insert-triple-set (take 5 (parse-file "/Users/sergio/Documents/Research/wordnet/rdf/wordnet-synset.ttl.gz")))

(defn comb-directory [dir]
  (let [dirf (File. dir)
        files (filter #(or (.endsWith (.getAbsolutePath %) ".ttl") (.endsWith (.getAbsolutePath %) ".ttl.gz")) (.listFiles dirf))]
    (dorun (map #(insert-triple-set (parse-file %)) files))))

(comb-directory "/Users/sergio/Documents/Research/wordnet/rdf")


