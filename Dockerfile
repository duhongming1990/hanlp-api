FROM registry.cn-hangzhou.aliyuncs.com/duhongming/alpine-java:8u212_jdk_unlimited_nashorn

MAINTAINER duhongming

WORKDIR /

EXPOSE 8080

ADD target/hanlp-api-*.jar app.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app.jar $PARAMS"]