package br.com.tsg_innovations.transactions_microservice.notification;

import br.com.tsg_innovations.transactions_microservice.transaction.Transaction;
import org.springframework.kafka.core.KafkaTemplate;

public class NotificationProducer {
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(Transaction transaction){
        kafkaTemplate.send("transaction-notification", transaction);
    }
}
