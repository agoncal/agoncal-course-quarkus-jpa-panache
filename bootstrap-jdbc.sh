#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.quarkus \
        -DprojectArtifactId=jdbc \
        -DclassName="org.agoncal.quarkus.jdbc.rest.CustomerResource" \
        -Dpath="/api/customers" \
        -Dextensions="resteasy-jsonb, jdbc-mariadb, quarkus-agroal"
