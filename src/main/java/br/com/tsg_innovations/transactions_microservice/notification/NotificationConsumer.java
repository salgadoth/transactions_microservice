package br.com.tsg_innovations.transactions_microservice.notification;

import br.com.tsg_innovations.transactions_microservice.exceptions.NotificationException;
import br.com.tsg_innovations.transactions_microservice.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestClient;

public class NotificationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    private RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder){
        this.restClient = builder.baseUrl(
            "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6")
        .build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "transactions_microservice")
    public void recieveNotification(Transaction transaction){
        LOGGER.info("Notifying transaction: ", transaction);

        var response = restClient.get().retrieve().toEntity(Notification.class);
        if(response.getStatusCode().isError() || !response.getBody().message())
            throw new NotificationException("Error notifying transaction: " + transaction);

        LOGGER.info("Notification has been sent: ", response.getBody());
    }
}
