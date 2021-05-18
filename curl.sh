#!/usr/bin/env bash
# Vintage Store
curl -H "Content-Type: application/json" -X POST http://localhost:8080/api/publishers -d '{"name": "Lorem"}' -v
curl http://localhost:8080/api/artists/2
