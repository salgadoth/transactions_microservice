package br.com.tsg_innovations.transactions_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@EnableJdbcAuditing
@SpringBootApplication
public class TransactionMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionMicroserviceApplication.class);
    }
}