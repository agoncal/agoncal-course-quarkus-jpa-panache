#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.course.quarkus.orm \
        -DprojectArtifactId=artist \
        -DpackageName="org.agoncal.quarkus.jdbc" \
        -Dextensions="jdbc-mysql, quarkus-agroal"
