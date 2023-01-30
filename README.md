<img src="https://img.shields.io/badge/Version-0.2.0-SNAPSHOT" alt="Downloads"/>
<img src="https://img.shields.io/badge/Spring%20Boot-2.4.13-blue" alt="Downloads"/>
<img src="https://img.shields.io/badge/SpringCloudFunction-3.2.8-blue" alt="Downloads"/>

# 项目介绍

## 项目地址

https://github.com/hankcs/HanLP/tree/1.x

## 项目启动

```
docker run --name hanlp-api -d -p 8080:8080 registry.cn-hangzhou.aliyuncs.com/duhongming/hanlp-api:0.2.0
```

## 项目DEBUG

```
hanlp.debug=true
```

## Swagger地址

http://localhost:8080/swagger-ui

http://localhost:8080/doc.html

### 自动摘要

```
curl -X POST "http://localhost:8080/hanlp/api/getSummary" -H "accept: */*" -H "Content-Type: application/json" -d "算法可大致分为基本算法、数据结构的算法、数论算法、计算几何的算法、图的算法、动态规划以及数值分析、加密算法、排序算法、检索算法、随机化算法、并行算法、厄米变形模型、随机森林算法。算法可以宽泛的分为三类，一，有限的确定性算法，这类算法在有限的一段时间内终止。他们可能要花很长时间来执行指定的任务，但仍将在一定的时间内终止。这类算法得出的结果常取决于输入值。二，有限的非确定算法，这类算法在有限的时间内终止。然而，对于一个（或一些）给定的数值，算法的结果并不是唯一的或确定的。三，无限的算法，是那些由于没有定义终止定义条件，或定义的条件无法由输入的数据满足而不终止运行的算法。通常，无限算法的产生是由于未能确定的定义终止条件。"
```

### 关键词提取

```
curl -X POST "http://localhost:8080/hanlp/api/extractKeyword" -H "accept: */*" -H "Content-Type: application/json" -d "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。"
```

### 命名实体识别

```
curl -X POST "http://localhost:8080/hanlp/api/tokenizer/name" -H "accept: */*" -H "Content-Type: application/json" -d "王国强、高峰、汪洋、张朝阳光着头、韩寒、小四武胜县新学乡政府大楼门前锣鼓喧天我在上海林原科技有限公司兼职工作"
```

### 分词计数

```
curl -X POST "http://localhost:8080/hanlp/api/tokenizer/counts" -H "accept: */*" -H "Content-Type: application/json" -d "我的希望是希望张晚霞的背影被晚霞映红"
```

### 汉字转拼音

```
curl -X POST "http://localhost:8080/hanlp/api/convertToPinyin" -H "accept: */*" -H "Content-Type: application/json" -d "重载不是重任"
```

## 版本历史

### 0.1.0

    [+] 标准分词+人名、地名、机构名识别接口
    [+] 摘要、关键词提取
    [+] 汉字转拼音

### 0.2.0

    [+] 导入数据包data-for-1.7.5.zip
    [^] NLP分词

### HanLP version

https://github.com/hankcs/HanLP/releases/tag/v1.8.3

### spring-boot version

https://spring.io/projects/spring-boot#learn

### spring-cloud-function version

https://spring.io/projects/spring-cloud-function#learn