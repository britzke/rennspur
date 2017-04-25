# Rennspur
Rennspur is a system for visualizing the traces of races at sporting events such as sailing races. Built with Java by the MiA17 @LetteVerein.

ToDo: Logo and example image

---

## Installation
It should run on any *NIX and Windows system with [Java 8](http://www.oracle.com/technetwork/java/index.html) and [Tomcat](https://tomcat.apache.org/).

## Installation with Docker
Use our automatic installer [(```docker-run.sh```)](https://github.com/britzke/rennspur/blob/master/docker-run.sh) to build and install it on your system.
#### Or follow the instructions:

git clone
```git clone https://github.com/britzke/rennspur.git && cd rennspur```
1) maven build process
    - default development environment/profile:
```docker run -it --rm --name rennspur-maven-build -v "$PWD":/usr/src/rennspur -w /usr/src/rennspur maven:alpine mvn package clean install```
    - production environment/profile:
```docker run -it --rm --name rennspur-maven-build -v "$PWD":/usr/src/rennspur -w /usr/src/rennspur maven:alpine mvn package clean install -Denvironment=prod```

> Add ```-DskipTests``` to skip the java tests. ```--rm``` automatically cleans up the container and remove the file system when the maven build container exits.

2) Derby database server:
```docker run -d --name rennspur-derby --net=host az82/docker-derby```

3) Tomcat Server:
```docker run -d --net=host --name rennspur-tomcat -v "$PWD"/target/rennspur-0.0.1-SNAPSHOT.war:/usr/local/tomcat/webapps/rennspur.war tomcat:8.5-alpine```

> ```--net=host``` is not not recommended! Use ```-p 8080:8080``` and ```-p 1527:1527``` on a [own bridge network](https://docs.docker.com/engine/userguide/networking/#a-bridge-network) to connect the database on port ```1527```.
