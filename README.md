```sh
╔═╗───────╔═══╗╔═══╗
║╬╠╗╔═╗╔╦╗║╔═╗║║╔══╝
║╔╣╚╣╬╚╣║║╚╝╔╝╠╣╚══╗
╚╝╚═╩══╬╗║╔═╝╔╩╩══╗║
───────╚═╝║║╚═╗╔══╝║
```

Evaluation of Play 2.5 API and performance using Gatling 2.2.x.
This project provides examples how to test simplest request and configure both server and client side for better response times and minimal CPU usage:

## Hardware required
- CPU: 2 cores or more
- RAM: 4Gb or greater

## Software installed required
- JDK: 1.8.0_x
- sbt: 0.13.x

## Building & running benchmarks
1. Build & run server
```sh
sbt clean stage
./target/universal/stage/bin/play
```

2. Compile and run benchmarks
```sh
sbt gatling:test
```

## Result of benchmarks
Please see `results` directory for benchmark reports on following environment:

Intel(R) Core(TM) i7-2640M CPU @ 2.80GHz (max 3.50GHz), RAM 12Gb DDR3-1333, Ubuntu 14.04.1, Linux 3.16.0-60-generic, Oracle JDK build 1.8.0_72-b15 64-bit
