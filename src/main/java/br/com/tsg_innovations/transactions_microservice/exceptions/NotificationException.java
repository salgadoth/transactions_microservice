package br.com.tsg_innovations.transactions_microservice.exceptions;

public class NotificationException extends RuntimeException {
    public NotificationException(String message) {
        super(message);
    }
}
