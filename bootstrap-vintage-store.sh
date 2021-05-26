#!/usr/bin/env bash
mvn -U io.quarkus:quarkus-maven-plugin:create \
        -DprojectGroupId=org.agoncal.bug.quarkus.devservice \
        -DprojectArtifactId=pojo \
        -DclassName="org.agoncal.bug.quarkus.devservice.pojo.Artist"
