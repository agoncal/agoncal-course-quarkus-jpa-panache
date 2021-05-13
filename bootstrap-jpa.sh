#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.quarkus \
        -DprojectArtifactId=jpa \
        -DclassName="org.agoncal.quarkus.jpa.rest.ArtistResource" \
        -Dpath="/api/artists" \
        -Dextensions="resteasy-jsonb, jdbc-mysql, hibernate-orm"
