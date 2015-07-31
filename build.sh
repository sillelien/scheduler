#!/usr/bin/env bash
set -ex
cd server
mvn clean install -Dmaven.test.skip=true
docker pull sillelien/java-slim
if [ -e /run/docker.sock ]
then
    socket=/run/docker.sock
else
    socket=/var/run/docker.sock
fi

if [ -n "$CIRCLE_CI" ]
then
    image="sillelien/scheduler:${CIRCLE_BRANCH}"
else
    image=scheduler
fi

docker run -ti -v ${socket}:/tmp/docker.sock -v $(pwd)/:/build/ sillelien/java-slim:0.1 ${image} scheduler target/tutum-scheduler-1.0-SNAPSHOT-jar-with-dependencies.jar src/main/resources sillelien.scheduler.Main
cd -
