#!/usr/bin/env bash
cd server
mvn clean install -Dmaven.test.skip=true

java -jar ../lib/packr.jar \
     -platform linux64 \
     -jdk "../lib//zulu1.8.0_45-8.7.0.5-x86lx64.zip" \
     -executable scheduler \
     -appjar target/tutum-scheduler-1.0-SNAPSHOT-jar-with-dependencies.jar \
     -mainclass "sillelien/scheduler/Main" \
     -vmargs "-Xmx256m" \
     -resources pom.xml \
     -minimizejre "hard" \
     -outdir ../out

cd -


