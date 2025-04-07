#!/bin/bash
docker build -t tomaszpakula/zadanie2:latest .
docker run -d --name zadanie2 tomaszpakula/zadanie2:latest
docker exec -d zadanie2 ngrok http 9000
