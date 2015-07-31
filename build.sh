#!/usr/bin/env bash
cd server
mvn clean install -Dmaven.test.skip=true
docker pull sillelien/java-slim
docker run -ti -v /var/run:/var/run -v $(pwd)/:/build/ sillelien/java-slim sillelien/scheduler scheduler target/tutum-scheduler-1.0-SNAPSHOT-jar-with-dependencies.jar src/main/resources sillelien.scheduler.Main
cd -
