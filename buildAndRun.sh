#!/bin/sh
mvn clean package && docker build -t it.ciacformazione/cloud .
docker rm -f cloud || true && docker run -d -p 8080:8080 -p 4848:4848 --name cloud it.ciacformazione/cloud 
