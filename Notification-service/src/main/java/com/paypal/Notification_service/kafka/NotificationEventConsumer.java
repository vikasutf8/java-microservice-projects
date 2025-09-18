package com.paypal.Notification_service.kafka;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.aspectj.weaver.ast.Not;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.paypal.Notification_service.Entity.Notification;
import com.paypal.Notification_service.Entity.Transaction;
import com.paypal.Notification_service.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Component

public class NotificationEventConsumer  {

    private final NotificationRepository notificationRepository;
    private final ObjectMapper mapper;

    public NotificationEventConsumer(NotificationRepository notificationRepository, ObjectMapper mapper) {
        this.notificationRepository = notificationRepository;
        this.mapper = mapper;
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @KafkaListener(topics = "payment-initiated", groupId = "notification-service-group")
    public void consumeTransactionEvent(Transaction transaction)  throws Exception {

       
        Notification notification = new Notification();

        String recieverUserId =transaction.getreceiverId().toString();
        notification.setUserId(recieverUserId);
        String senderUserId = transaction.getsenderId().toString();
        String notify = "$" + transaction.getAmount() + " has been sent to user " + recieverUserId + " from user " + senderUserId;
        notification.setMessage(notify);

        LocalDateTime now = LocalDateTime.now();
        notification.setSendAt(now);
     
        notificationRepository.save(notification);

    }
}
