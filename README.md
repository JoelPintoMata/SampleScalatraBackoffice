# Sample Scalatra Rest API #

## Build & Run ##

```sh
$ cd tvi_backoffice
$ ./sbt
> jetty:start
> browse
```
If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.

## Build & Run + Automatic code reloading##
```sh
$ sbt
> ~;jetty:stop;jetty:start
```

## Supporting documentation

http://scalatra.org/getting-started/first-project.html
http://scalatra.org//guides/2.4/formats/json.html
http://www.sdfonlinetester.info/#

## URL Endpoints

Method  Endpoint    Description                                     Default
POST    /tariff     Sets a tariff                                   http://localhost:8080/tariff
POST    /scpp       Registers a scpp                                http://localhost:8080/scpp
GET     /overview   Gets an overview of the scpp(s) per customer    http://localhost:8080/overview

### Tests

#### Unit tests

```sh
$ sbt test
```

#### Integration tests

At src/test/resources implemented via [https://www.getpostman.com//](PostMan) scripts