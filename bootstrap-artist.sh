#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.bug.quarkus \
        -DprojectArtifactId=orm \
        -DclassName="org.agoncal.bug.quarkus.orm.ArtistRepository" \
        -Dextensions="jdbc-postgresql, hibernate-orm-panache"
