#!/bin/sh

# Pull latest version from git
echo Pull latest version from git...
git pull origin master
echo Rennspur version up to date.

docker -v
if [ $? -ne 0 ]; then
    exit "Sorry, docker is not installed. Check https://docs.docker.com/engine/installation/"
else
    echo "Continuing with docker installation"
fi

# run maven build
echo Start with maven build...
docker run -it --rm --name rennspur-maven-build -v "$PWD":/usr/src/rennspur -w /usr/src/rennspur maven:alpine mvn package clean install -DskipTests -Denvironment=prod
echo maven build complete.
# ToDo: Check for error...

# run database server derby
echo Start database derby server...
docker run -d --name rennspur-derby --net=host az82/docker-derby
echo Derby database server is running.
# ToDo: Check for database connection...

# run Tomcat
echo Start Tomcat server...
docker run -d --net=host --name rennspur-tomcat -v "$PWD"/target/rennspur-0.0.1-SNAPSHOT.war:/usr/local/tomcat/webapps/rennspur.war tomcat:8.5-alpine
echo Tomcat server is running.
# ToDo: Check for error...

exit 0