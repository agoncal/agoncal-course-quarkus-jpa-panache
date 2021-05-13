#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.quarkus \
        -DprojectArtifactId=jdbc \
        -DclassName="org.agoncal.quarkus.jdbc.repository.CustomerRepository" \
        -Dextensions="jdbc-mariadb, quarkus-agroal"
