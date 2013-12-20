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

1. Download WordNet with: cd $WORDNET_ROOT ; git clone git://eculture.cs.vu.nl/home/git/vocs/wordnet
2. Restart and stop the Neo4j server: cd $NEO4J ; bin/neo4j restart ; bin/neo4j stop
3. Backup your Neo4j database if needed: cd $NEO4J ; mv data data.backup
3. Run WordnetLoader with two arguments:
```
java -jar target/wordnet2neo4j-0.1.0-SNAPSHOT-standalone.jar $WORDNET_ROOT/wordnet/rdf $NEO4J/data/graph.db
```
4. Start the Neo4j server: cd $NEO4J ; bin/neo4j start


