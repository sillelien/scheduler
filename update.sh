#!/usr/bin/with-contenv sh

set -ex

if [ ! -d /config/.git ]
then
   git clone git://${SCSERVER_PORT_9418_TCP_ADDR}:${SCSERVER_PORT_9418_TCP_PORT}/config /config
fi

cd /config

if [ ! -d ./sillelien.scheduler/schedules.d/ ]
then
    git pull origin master || git push -u origin master
    mkdir -p ./sillelien.scheduler/schedules.d/
    echo "# Edit me" >  ./sillelien.scheduler/schedules.d/schedule.yml
    echo "Please edit the file  ./sillelien.scheduler/schedules.d/schedule.yml " > README.sillelien.scheduler.md
    git add ./sillelien.scheduler/schedules.d/schedule.yml
    git add README.sillelien.scheduler.md
    git commit -am "Initial check in for configuring Sillelien Scheduler"
    git push -u origin master
fi

while true
do
    git pull origin master
    sleep 10
done
