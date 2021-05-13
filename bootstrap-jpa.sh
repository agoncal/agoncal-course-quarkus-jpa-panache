#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.quarkus \
        -DprojectArtifactId=jpa \
        -DclassName="org.agoncal.quarkus.jpa.repository.ArtistRepository" \
        -Dextensions="jdbc-mysql, hibernate-orm"
