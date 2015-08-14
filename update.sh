#!/usr/bin/with-contenv sh

git config --global user.email "scheduler-server@$(hostname)"
git config --global user.name "Sillelien Scheduler Server @ $(hostname)"

set -e

if [ ! -d /config/.git ]
then
   git clone git://${SCSERVER_PORT_9418_TCP_ADDR}:${SCSERVER_PORT_9418_TCP_PORT}/config /config
   cd /config
   git checkout -b master
   git fetch --all
   git push -u origin master
else
   cd /config
   git remote set-url origin git://${SCSERVER_PORT_9418_TCP_ADDR}:${SCSERVER_PORT_9418_TCP_PORT}/config
   git branch --set-upstream-to=origin/master master
fi

cd /config

if [ ! -d /config/sillelien.scheduler/schedules.d/ ]
then
    git pull origin master || git push -u origin master
    mkdir -p /config/sillelien.scheduler/schedules.d/
    [ -f ./sillelien.scheduler/schedules.d/schedule.yml ] || ( echo "# Edit me" >  ./sillelien.scheduler/schedules.d/schedule.yml && git add ./sillelien.scheduler/schedules.d/schedule.yml )
    [ -f README.sillelien.scheduler.md ] || ( echo "Please edit the file  ./sillelien.scheduler/schedules.d/schedule.yml " > README.sillelien.scheduler.md &&  git add README.sillelien.scheduler.md )


    git commit -am "Initial check in for configuring Sillelien Scheduler"
    git push -u origin master
fi

while true
do
    git pull
    sleep 10
done
