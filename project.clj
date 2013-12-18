(defproject wordnet2neo4j "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.tinkerpop.blueprints/blueprints-neo4j-graph "2.4.0"]
                 [com.tinkerpop.blueprints/blueprints-graph-sail "2.4.0"]]
  :java-source-paths ["java"]
  :plugins [[no-man-is-an-island/lein-eclipse "2.0.0"]])
