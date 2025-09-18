package com.paypal.Notification_service.controller;

import java.util.List;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.Notification_service.Entity.Notification;
import com.paypal.Notification_service.service.NotificationService;

import lombok.Data;

@RestController
@RequestMapping("/api/v1/notifications")
@Data
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("")
    public Notification sendNotification(Notification notification) {
        return notificationService.sendNotification(notification);
    }

    @GetMapping("/{userId}")
    public List<Notification> getNotificationsByUserId(@PathVariable String userId) {
        return notificationService.getNotificationsByUserId(userId);
    }
}
