#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.course.quarkus.orm \
        -DprojectArtifactId=vintage-store \
        -DpackageName="org.agoncal.quarkus.panache" \
        -Dextensions="jdbc-postgresql, hibernate-orm-panache"
