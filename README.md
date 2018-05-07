```sh
╔═╗───────╔═══╗╔═══╗
║╬╠╗╔═╗╔╦╗║╔═╗║║╔══╝
║╔╣╚╣╬╚╣║║╚╝╔╝╠╣╚══╗
╚╝╚═╩══╬╗║╔═╝╔╩╣╔═╗║
───────╚═╝║  ╚╗║╚═╝║
```

Evaluation of [Play](https://github.com/playframework/playframework) 2.6 API and performance using 
[Wrk 2](https://github.com/giltene/wrk2).

This project provides examples how to test simplest request and configure server side for better response times and minimal CPU usage:

## Building & running benchmarks
1. Build & run server
```sh
sbt clean stage
./target/universal/stage/bin/play
```

2. Run benchmarks

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
Please see `results` directory for benchmark results using different JDK and GraalVM versions on the following env.:
Intel® Core™ i7-7700HQ CPU @ 2.8GHz (max 3.8GHz), RAM 16Gb DDR4-2400, Ubuntu 18.04, latest versions of Oracle JDK 8/10 
and GraalVM CE/EE