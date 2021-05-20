#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.course.quarkus.orm \
        -DprojectArtifactId=customer \
        -DclassName="org.agoncal.quarkus.jpa.CustomerRepository" \
        -Dextensions="jdbc-mariadb, hibernate-orm, hibernate-validator"
