# score-board

Simple library to show football matches based on ConcurrentHashMap
according to the assumption that data should be updated as faster as can be.
During the implementation was changed data structure according to assumption that dara should be updated fast 
and according to some overhead with getting and adding records to the ConcurrentSkipListSet.

Build with tests :
mvn clean install