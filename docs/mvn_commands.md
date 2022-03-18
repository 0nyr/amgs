# compile and build the jar 
mvn package -e

# run the jar
mvn exec:java -e

# assemble projet to zip
mvn assembly:single -e
