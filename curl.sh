#!/usr/bin/env bash
# JDBC - MariaDB
curl -H "Content-Type: application/json" -X POST http://localhost:8701/api/customers -d '{"firstName": "Sunni", "lastName": "Stokes", "email": "saul.hansen@yahoo.com"}' -v
curl http://localhost:8701/api/customers/149201082
# JPA - MySQL
curl -H "Content-Type: application/json" -X POST http://localhost:8702/api/artists -d '{"firstName": "Sunni", "lastName": "Stokes", "bio": "lorem"}' -v
curl http://localhost:8702/api/artists/2
