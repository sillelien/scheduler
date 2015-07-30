#!/usr/bin/env bash
cd server && mvn clean install -Dmaven.test.skip=true && cd -
docker pull sillelien/cyborg-wrapper:master
docker run -ti -v /var/run/docker.sock:/var/run/docker.sock -v $(pwd)/server:/build sillelien/cyborg-wrapper:master tutum-scheduler scheduler
