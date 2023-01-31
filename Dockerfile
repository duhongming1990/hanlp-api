# https://docs.docker.com/engine/reference/builder/

FROM registry.cn-hangzhou.aliyuncs.com/duhongming/alpine-java:8u212_jdk_unlimited_nashorn

# MAINTAINER duhongming
LABEL com.github.hanlp.api.image.author="duhongming"
LABEL com.github.hanlp.api.version="0.2.0"
LABEL com.github.hanlp.data.version="1.7.5"

# import data-for-1.7.5.zip
WORKDIR /HanLP
COPY data/data-for-1.7.5.zip /HanLP
RUN unzip data-for-1.7.5.zip && rm -rf data-for-1.7.5.zip

# import hanlp-api.jar
WORKDIR /
COPY target/hanlp-api-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app.jar $PARAMS"]