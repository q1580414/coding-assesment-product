# Interview Process Coding Assesment


### Prerequisites

* Java 1.8
* Git
* Maven 3.3

### Installing

just clone whole source code as follows and go to coding-assesment-product folder
```
git clone https://github.com/q1580414/coding-assesment-product
```
```
cd coding-assesment-product
```

### Running the tests

```
mvn test
```

### Running application

```
mvn spring-boot:run
```

### Test application

Just call GET Http with urls below with browser or PostMan or another http client 

```
http://localhost:8080/product/reduction
http://localhost:8080/product/reduction?labelType=ShowWasNow
http://localhost:8080/product/reduction?labelType=ShowWasThenNow
http://localhost:8080/product/reduction?labelType=ShowPercDscount
```
