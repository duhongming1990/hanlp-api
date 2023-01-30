FROM registry.cn-hangzhou.aliyuncs.com/duhongming/alpine-java:8u212_jdk_unlimited_nashorn

MAINTAINER duhongming

RUN mkdir /HanLP
WORKDIR /HanLP
COPY data/data-for-1.7.5.zip /HanLP
RUN unzip data-for-1.7.5.zip && rm -rf data-for-1.7.5.zip

WORKDIR /
ADD target/hanlp-api-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app.jar $PARAMS"]