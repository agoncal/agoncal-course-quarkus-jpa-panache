#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.course.quarkus.orm \
        -DprojectArtifactId=artist \
        -DclassName="org.agoncal.quarkus.jdbc.ArtistRepository" \
        -Dextensions="jdbc-mysql, quarkus-agroal"
