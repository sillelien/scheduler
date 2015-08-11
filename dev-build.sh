#!/usr/bin/env bash
set -ex
cd server
mvn clean install -Dmaven.test.skip=true
cd -
docker-compose build && docker-compose up

