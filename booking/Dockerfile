###########
#  Maven  #
###########################################################################
# In this stage the docker file will copy 'pom.xml' and 'src' directory.  #
# 'mvn clean package' will compile the code and create a jar file.        #
###########################################################################
FROM maven:3.8.4-openjdk-11-slim AS maven
WORKDIR application
COPY pom.xml .
COPY src ./src
ARG PORT
RUN mvn -Dmaven.test.skip clean package

# FROM https://github.com/PySualk/spring-cloud-function-openfaas/blob/master/function/Dockerfile
FROM openfaas/of-watchdog:0.7.6 as watchdog

FROM openjdk:11-jre-slim as ship
RUN apt-get update -qqy \
  && apt-get install -qqy \
   --no-install-recommends \
   unzip
RUN addgroup --system app \
    && adduser --system --ingroup app app

COPY --from=watchdog /fwatchdog /usr/bin/fwatchdog
RUN chmod +x /usr/bin/fwatchdog

WORKDIR /home/app
COPY --from=maven application/target/*.jar application.jar

user app
RUN find .

WORKDIR /home/app/

ENV upstream_url="http://127.0.0.1:8888"
ENV mode="http"
ENV CLASSPATH="/home/app/application.jar:/home/app/function-1.0/lib/*"

ENV fprocess="java -jar /home/app/application.jar"
EXPOSE 8080 8888

HEALTHCHECK --interval=5s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]
