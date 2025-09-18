package com.paypal.Notification_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paypal.Notification_service.Entity.Notification;

@Service
public interface NotificationService {

    Notification sendNotification(Notification notification);
    List<Notification> getNotificationsByUserId(String userId);
}
