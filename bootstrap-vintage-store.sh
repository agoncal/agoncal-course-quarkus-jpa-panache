#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.course.quarkus.orm \
        -DprojectArtifactId=vintage-store \
        -DclassName="org.agoncal.quarkus.panache.repository.ArtistRepository" \
        -Dextensions="jdbc-postgresql, hibernate-orm-panache"
