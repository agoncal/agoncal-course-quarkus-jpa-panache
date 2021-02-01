#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.quarkus \
        -DprojectArtifactId=jpa-panache \
        -DclassName="org.agoncal.quarkus.panache.rest.ItemResource" \
        -Dpath="/api/items" \
        -Dextensions="hibernate-orm,resteasy-jsonb"
