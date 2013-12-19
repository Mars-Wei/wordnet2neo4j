wordnet2neo4j
=============

Wrapper around joshsh's implementation of an importer from WordNet to Neo4j

Please check:

https://gist.github.com/joshsh/5047451

## Requirements

* Neo4j, version 1.9.4 (does NOT work with version 2.0.0)
 * http://www.neo4j.org/download_thanks?edition=community&release=1.9.4&platform=unix

## Build

```bash
$ lein uberjar
```

## Instructions

Assuming $WORDNET_DIR is the folder where you want to download WordNet 3.0 and $NEO4J the folder where Neo4j is installed:

1. Download WordNet with: cd $WORDNET_DIR ; git clone git://eculture.cs.vu.nl/home/git/vocs/wordnet
2. Stop the Neo4j server: cd $NEO4J ; bin/neo4j stop
3. Run WordnetLoader with two arguments:
```bash
java -jar target/wordnet2neo4j-0.1.0-SNAPSHOT-standalone.jar $WORDNET_DIR $NEO4J/data/graph.db
```
4. Start the Neo4j server: cd $NEO4J ; bin/neo4j start


