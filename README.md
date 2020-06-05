# Cassandra Scaffolding


## Steps to create scaffolding 

1) Start Cassandra Query Language Shell using commandline:  
```
cqlsh
```
 
2) Create keyspace, table, insert a record
 
```
CREATE KEYSPACE test WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1};

use test ;

CREATE TABLE account_profile(id int PRIMARY KEY, name text, max_rating int);

INSERT INTO test.account_profile( id, name, max_rating) values( 1, 'kevin duraj', 100);
```
