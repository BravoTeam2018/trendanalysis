# TrendAnalysis add-on module

     
- [TrendAnalysis](https://github.com/BravoTeam2018/trendanalysis)  
    - Trend analysis tool used by Security Teams to detect trends and take action
    - A Springboot micro service + Elastic Stack deployed on Azure 
    - [Source repository](https://github.com/BravoTeam2018/trendanalysis)

#### Architecture Context Diagram 
![Context Diagram](https://github.com/BravoTeam2018/DetectCloneRFID/blob/master/docs/ContextDiagram.png)

## Sample screens

#### Trend DashBoard
![Dashboard](https://github.com/BravoTeam2018/DetectCloneRFID/blob/master/docs/dashboard.png)

### Building from source

### Prerequisites 
1. Install [Maven 3]( https://maven.apache.org/)
2. Install [java 8]( http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)



### Building Code
```
mvnw package
```

### Running tests
```
mvnw test
```

## Running the webservice on your local machine
```
mvnw spring-boot:run
```

## Running Sonarqube Analysis 
```
mvnw sonar:sonar
```

