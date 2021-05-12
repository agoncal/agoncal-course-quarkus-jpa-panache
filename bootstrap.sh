#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.quarkus \
        -DprojectArtifactId=jpa-panache \
        -DclassName="org.agoncal.quarkus.orm.rest.ItemResource" \
        -Dpath="/api/items" \
        -Dextensions="hibernate-orm,hibernate-orm-panache,resteasy-jsonb"
