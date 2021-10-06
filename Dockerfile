FROM azul/zulu-openjdk-alpine:13-jre

#Step 2
COPY target/atm-machine-1.0-SNAPSHOT.jar /tmp

#Step 3
COPY myconfigfile.txt /tmp
ENV CONFIG_FILE /tmp/myconfigfile.txt

#Step 4
#CMD java -jar /tmp/myApp.jar
ENTRYPOINT java -jar /tmp/atm-machine-1.0-SNAPSHOT.jar