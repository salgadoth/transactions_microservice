package br.com.tsg_innovations.transactions_microservice.notification;

import br.com.tsg_innovations.transactions_microservice.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    NotificationProducer notificationProducer;

    public NotificationService(){}

    public void notify(Transaction newTransaction) {
        LOGGER.info("Notifying transaction: ", newTransaction);

        notificationProducer.sendNotification(newTransaction);
    }
}
