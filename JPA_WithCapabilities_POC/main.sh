#!/bin/bash

echo "> gradle build"
gradle build

cp="libs/gson-2.3.jar;"
cp+="libs/postgresql-9.2-1004.jdbc4.jar;"
cp+="libs/openjpa-all-2.3.0.jar;"
cp+="libs/slf4j-api-1.7.7.jar;"
cp+="libs/slf4j-simple-1.7.7.jar;"
cp+="build/libs/JPA_WithCapabilities_POC.jar"

echo ""
echo "> java -cp $cp com.michielvanderlee.jpa.Main"
java -cp "$cp" com.michielvanderlee.jpa.Main
