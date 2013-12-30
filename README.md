wordnet2neo4j
=============

Wrapper around joshsh's implementation of an importer from WordNet to Neo4j

Please check:

https://gist.github.com/joshsh/5047451

## Requirements

* Neo4j, version 1.9.5 (does NOT work with version 2.0.0)
 * http://www.neo4j.org/download_thanks?edition=community&release=1.9.4&platform=unix

## Build

```bash
$ lein uberjar
```

## Instructions

Assuming $WORDNET_DIR is the folder where you want to download WordNet 3.0 and $NEO4J the folder where Neo4j is installed:

1. Download WordNet with: cd $WORDNET_ROOT ; git clone git://eculture.cs.vu.nl/home/git/vocs/wordnet
2. Disable Lucene indexes in $NEO4J/conf/neo4j.properties:
```
# Autoindexing

# Enable auto-indexing for nodes, default is false
#node_auto_indexing=true

# The node property keys to be auto-indexed, if enabled
#node_keys_indexable=value

# Enable auto-indexing for relationships, default is false
#relationship_auto_indexing=true

# The relationship property keys to be auto-indexed, if enabled
#relationship_keys_indexable=value
```
3. Restart and stop the Neo4j server: cd $NEO4J ; bin/neo4j restart ; bin/neo4j stop
4. Backup your Neo4j database if needed: cd $NEO4J ; mv data data.backup
5. Run WordnetLoader with two arguments:
```
$ lein run $WORDNET_ROOT/wordnet/rdf/full $NEO4J/data/graph.db
```
6. Start the Neo4j server: cd $NEO4J ; bin/neo4j start



