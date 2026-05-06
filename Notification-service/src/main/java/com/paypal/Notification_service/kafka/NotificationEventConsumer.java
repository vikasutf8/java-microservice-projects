package com.paypal.Notification_service.kafka;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.paypal.Notification_service.Entity.Notification;
import com.paypal.Notification_service.Entity.Transaction;
import com.paypal.Notification_service.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

//@Component
//public class NotificationEventConsumer  {
//
//    private final NotificationRepository notificationRepository;
//
//    public NotificationEventConsumer(NotificationRepository notificationRepository, ObjectMapper mapper) {
//        this.notificationRepository = notificationRepository;
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//    }
//
//    @KafkaListener(topics = "payment-initiated", groupId = "notification-service-group")
//    public void consumeTransactionEvent(Transaction transaction)  throws Exception {
//
//
//        Notification notification = new Notification();
//
//        String recieverUserId =transaction.getreceiverId().toString();
//        notification.setUserId(recieverUserId);
//        String senderUserId = transaction.getsenderId().toString();
//        String notify = "$" + transaction.getAmount() + " has been sent to user " + recieverUserId + " from user " + senderUserId;
//        notification.setMessage(notify);
//
//        LocalDateTime now = LocalDateTime.now();
//        notification.setSendAt(now);
//        // TODO: handle exceptions and retries if saving to database fails -IMPORTNAT
//        notificationRepository.save(notification);
//
//    }
//}


@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationEventConsumer {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "payment-transactions", groupId = "notification-service-group")
    public void consumeTransactionEvent(@Payload Transaction transaction) {

        log.info("📥 Received transaction: {}", transaction);

        Notification notification = new Notification();

        String receiverUserId = transaction.getreceiverId().toString();
        String senderUserId = transaction.getsenderId().toString();

        notification.setUserId(receiverUserId);
        notification.setMessage(
                "$" + transaction.getAmount() +
                        " sent from user " + senderUserId +
                        " to user " + receiverUserId
        );

        notification.setSendAt(LocalDateTime.now());

        try {
            notificationRepository.save(notification);
            log.info("✅ Notification saved");
        } catch (Exception e) {
            log.error("❌ Failed to save notification", e);
            throw e; // important for retry
        }
    }
}
