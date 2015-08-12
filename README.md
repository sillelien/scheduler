${HEADER}

# Scheduler ${STATE_EXPERIMENTAL}

Scheduler is very much a work in progress. It's aim is to provide work scheduling for distributed Docker based systems.

${BLURB}

Please use a tagged version:

```
FROM sillelien/scheduler:${RELEASE}
```

## Creating a Schedule

Sillelien Scheduler, uses Sillelien Configurator to configure schedules. Configurator is simply a combination of a git repository and an editor to allow you to alter config in Docker images in a sensible and permanent manner.

Below is a typical docker-compose file

```yaml

worker:
  image: sillelien/jessie
  command: "sleep 1000000"


scheduler:
  image: sillelien/scheduler:${RELEASE}
  links:
    - test:test
    - scserver:scserver

scserver:
  image: sillelien/sc-server:0.0.56
  volumes:
    - /var/sc/config/server:/repo
  ports:
    - "1500"
    - "9418"

sceditor:
  image: sillelien/sc-editor:0.0.56
  volumes:
    - /var/sc/config/editor:/config
  links:
    - scserver:scserver
  ports:
    - "8080:8080"

```

Here you can see the three parts: the Configurator server, the Configurator editor and the Scheduler. The editor allows you to create and modify Scheduler YAML files, the server stores them in a git repository and Scheduler uses them to Schedule work. 

When you first start up this docker-compose arrangement Scheduler will create the appropriate directories and files for you to use.

${TUTUM}

## Badges

[![Docker Registry](https://img.shields.io/docker/pulls/sillelien/scheduler.svg)](https://registry.hub.docker.com/u/sillelien/scheduler)

[![Image Layers](https://badge.imagelayers.io/sillelien/scheduler.svg)](https://imagelayers.io/?images=sillelien/scheduler:master 'Get your own badge on imagelayers.io') 

[![Circle CI](https://circleci.com/gh/sillelien/scheduler/tree/master.svg?style=svg)](https://circleci.com/gh/sillelien/scheduler/tree/master)

${FOOTER}
