# Interview Process Coding Assesment


### Prerequisites

* Java 1.8
* Git
* Maven 3.3

### Installing

just pull whole source code as follows
```
git pull https://github.com/q1580414/coding-assesment-product
```

### Running the tests

```
mvn test
```

### Running application

Open terminal. Go to application folder which contains pom.xml and run command

```
mvn spring-boot:run
```

### Test application

Just call GET http with urls below 

```
http://localhost:8080/product/reduction
http://localhost:8080/product/reduction?labelType=ShowWasNow
http://localhost:8080/product/reduction?labelType=ShowWasThenNow
http://localhost:8080/product/reduction?labelType=ShowPercDscount
```
