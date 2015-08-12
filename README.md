

# Scheduler [![Experimental](https://img.shields.io/badge/Status-Experimental_or_POC-red.svg?style=flat)](http://github.com/sillelien/scheduler)

Scheduler is very much a work in progress. It's aim is to provide work scheduling for distributed Docker based systems.

-------

**If you use this project please consider giving us a star on [GitHub](http://github.com/sillelien/scheduler). Also if you can spare 30 secs of your time please let us know your priorities here https://sillelien.wufoo.com/forms/zv51vc704q9ary/  - thanks, that really helps!**

Please contact us through chat or through GitHub Issues.

[![GitHub Issues](https://img.shields.io/github/issues/sillelien/scheduler.svg)](https://github.com/sillelien/scheduler/issues)

[![Join the chat at https://gitter.im/sillelien/scheduler](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/sillelien/scheduler?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

-------

Please use a tagged version:

```
FROM sillelien/scheduler:0.0.54
```

## Creating a Schedule

Sillelien Scheduler, uses [Sillelien Configurator](https://github.com/sillelien/configurator) to configure schedules. Configurator is simply a combination of a git repository and an editor to allow you to alter config in Docker images in a sensible and permanent manner.

Below is a typical docker-compose file

```yaml

worker:
  image: sillelien/jessie
  command: "sleep 1000000"


scheduler:
  image: sillelien/scheduler:0.0.54
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

[![Deploy to Tutum](https://s.tutum.co/deploy-to-tutum.svg)](https://dashboard.tutum.co/stack/deploy/)

## Badges

[![Docker Registry](https://img.shields.io/docker/pulls/sillelien/scheduler.svg)](https://registry.hub.docker.com/u/sillelien/scheduler)

[![Image Layers](https://badge.imagelayers.io/sillelien/scheduler.svg)](https://imagelayers.io/?images=sillelien/scheduler:master 'Get your own badge on imagelayers.io') 

[![Circle CI](https://circleci.com/gh/sillelien/scheduler/tree/master.svg?style=svg)](https://circleci.com/gh/sillelien/scheduler/tree/master)

--------

[![GitHub License](https://img.shields.io/github/license/sillelien/scheduler.svg)](https://raw.githubusercontent.com/sillelien/scheduler/master/LICENSE)

(c) 2015 Sillelien all rights reserved. Please see [LICENSE](https://raw.githubusercontent.com/sillelien/scheduler/master/LICENSE) for license details of this project. Please visit http://sillelien.com for help and commercial support or raise issues on [GitHub](https://github.com/sillelien/scheduler/issues).
