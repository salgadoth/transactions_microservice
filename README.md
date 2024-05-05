<h1 align="center">Transactions Microservice (a Senior Back-End Challenge)</h1>
<p align="center">
  <a href="https://www.linkedin.com/in/thiago-salgado-3a1174200/">
    <img src="https://img.shields.io/badge/LinkedIn-blue" />
  </a>
  
  <a href="https://medium.com/@salgadoth">
    <img src="https://img.shields.io/badge/medium-black" />
  </a>
</p>


Project elaborated to solve <a href="https://github.com/PicPay/picpay-desafio-backend?tab=readme-ov-file">this</a> codding challenge.

<p align="center">
  <a href="#key-technologies">Key Technologies</a> •
  <a href="#how-to-deploy">How To Deploy</a> •
  <a href="#api">API</a> •
  <a href="#architecture">Architectures</a> 
</p>

## Key Technologies

* <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/">SpringBoot</a>
* <a href="https://docs.spring.io/spring-framework/reference/web/webmvc.htm">Spring MVC</a>
* <a href="https://docs.spring.io/spring-data/jdbc/docs/2.4.18/reference/html/">SpringData JDBC</a>
* <a href="https://spring.io/projects/spring-kafka">Spring for Apache Kafka</a>
* <a href="https://docs.docker.com/compose/">Docker Compose</a>
* <a href="https://www.h2database.com/html/tutorial.html">H2</a>

## How To Deploy

To clone and run this application, you'll need [Git](https://git-scm.com), [Java 21](https://www.oracle.com/au/java/technologies/downloads/)), [Docker Engine](https://docs.docker.com/engine/install/), an IDE of your choice to execute the SpringBoot application (I recommend IntelliJ) installed on your computer. From your command line:

```bash
# Clone this repository
$ git clone https://github.com/giuliana-bezerra/transactions_microservice.git

# Go into the repository
$ cd transactions_microservice

# Start Kafka docker container
$ docker-compose up

# Open SpringBoot application in your preferred IDE and execute it.
```

## API
```bash
# New transaction
POST :8080/transaction

Body:
{
  "value": 100,
  "payee": 1,
  "payer: 2
}

# Get transactions
GET: :8080/transaction
```

## Architecture
> **Service Architecture**
<img src="https://github.com/salgadoth/transactions_microservice/blob/master/.github/Service%20Architecture.png" />

> **API Architecture**
<img src="https://github.com/salgadoth/transactions_microservice/blob/master/.github/API%20Architecture.png" />
