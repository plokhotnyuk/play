```sh
╔═╗───────╔═══╗╔═══╗
║╬╠╗╔═╗╔╦╗║╔═╗║║╔══╝
║╔╣╚╣╬╚╣║║╚╝╔╝╠╣╚══╗
╚╝╚═╩══╬╗║╔═╝╔╩╣╔═╗║
───────╚═╝║  ╚╗║╚═╝║
```

Evaluation of Play 2.6 API and performance using Wrk 2.

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
Please see `results` directory for benchmark reports on following environment:

Intel(R) Core(TM) i7-2760QM CPU @ 2.40GHz (max 3.50GHz), RAM 16Gb DDR3-1600, Ubuntu 15.04, Linux 4.4.0-38-generic, Oracle JDK build 1.8.0_112-b15 64-bit