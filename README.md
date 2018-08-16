```sh
╔═╗───────╔═══╗╔═══╗
║╬╠╗╔═╗╔╦╗║╔═╗║║╔══╝
║╔╣╚╣╬╚╣║║╚╝╔╝╠╣╚══╗
╚╝╚═╩══╬╗║╔═╝╔╩╣╔═╗║
───────╚═╝║  ╚╗║╚═╝║
```

Evaluation of [Play](https://github.com/playframework/playframework) 2.8 API and performance using 
[Wrk 2](https://github.com/giltene/wrk2).

This project provides examples how to test simplest request and configure server side for better response times and minimal CPU usage:

## Building & running benchmarks

### Build the server

```sh
sbt clean stage
sed -i -e 's/1.6/1/g' ./target/universal/stage/bin/play
```

### Run the server

- Corretto 8:
```sh
export JAVA_HOME=/usr/lib/jvm/corretto-8
export JAVA_OPTS="-server -Xms2g -Xmx2g -XX:NewSize=1g -XX:MaxNewSize=1g -XX:+UseParallelGC -XX:-UseBiasedLocking -XX:+AlwaysPreTouch"
./target/universal/stage/bin/play
```

- OpenJDK 16:
```sh
export JAVA_HOME=/usr/lib/jvm/openjdk-16
export JAVA_OPTS="-server -Xms2g -Xmx2g -XX:NewSize=1g -XX:MaxNewSize=1g -XX:+UseParallelGC -XX:-UseBiasedLocking -XX:+AlwaysPreTouch"
./target/universal/stage/bin/play
```

- OpenJDK 10 + Graal:
```sh
export JAVA_HOME=/usr/lib/jvm/openjdk-16
export JAVA_OPTS="-server -Xms2g -Xmx2g -XX:NewSize=1g -XX:MaxNewSize=1g -XX:+UseParallelGC -XX:-UseBiasedLocking -XX:+AlwaysPreTouch -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler"
./target/universal/stage/bin/play
```

- GraalVM CE Java 8:
```sh
export JAVA_HOME=/usr/lib/jvm/graalvm-ce-java8
export JAVA_OPTS="-server -Xms2g -Xmx2g -XX:NewSize=1g -XX:MaxNewSize=1g -XX:+UseParallelGC -XX:-UseBiasedLocking -XX:+AlwaysPreTouch"
./target/universal/stage/bin/play
```

- GraalVM EE Java 8:
```sh
export JAVA_HOME=/usr/lib/jvm/graalvm-ee-java8
export JAVA_OPTS="-server -Xms2g -Xmx2g -XX:NewSize=1g -XX:MaxNewSize=1g -XX:+UseParallelGC -XX:-UseBiasedLocking -XX:+AlwaysPreTouch"
./target/universal/stage/bin/play
```

### Run benchmarks

Server need to be warmed up before to be able handle max request rate. Use option `-R` with half of max value for that.

JSON GET:
```sh
./wrk -c50 -d1m -t4 -R65000 -L -v http://localhost:9000/json 
```

JSON POST:
```sh
./wrk -c50 -d1m -t4 -R30000 -L -v -s src/test/lua/jsonPost.lua http://localhost:9000/json 
```

Plain text GET:
```sh
./wrk -c50 -d1m -t4 -R65000 -L -v http://localhost:9000/plaintext 
```

Plain test POST:
```sh
./wrk -c50 -d1m -t4 -R30000 -L -v -s src/test/lua/plaintextPost.lua http://localhost:9000/plaintext 
```

## Result of benchmarks
Please see [results](https://github.com/plokhotnyuk/play/tree/master/results/wrk2) directory for benchmark results using 
different JDK and GraalVM versions on the following environment: Intel® Core™ i7-7700HQ CPU @ 2.8GHz (max 3.8GHz), 
RAM 16Gb DDR4-2400, Ubuntu 18.04, latest versions of Oracle JDK 8/10 and GraalVM CE/EE