package com.paypal.Notification_service.controller;

import java.util.List;

import com.paypal.Notification_service.Dto.ApiResponse;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.paypal.Notification_service.Entity.Notification;
import com.paypal.Notification_service.service.NotificationService;

import lombok.Data;

@RestController
@RequestMapping("/api/v1/notifications")
@Data
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

//    @PostMapping("")
//    public Notification sendNotification(Notification notification) {
//        return notificationService.sendNotification(notification);
//    }
//
//    @GetMapping("/{userId}")
//    public List<Notification> getNotificationsByUserId(@PathVariable String userId) {
//        return notificationService.getNotificationsByUserId(userId);
//    }

    @PostMapping
    public ResponseEntity<ApiResponse<Notification>> sendNotification(
            @RequestBody Notification notification) {

        Notification saved = notificationService.sendNotification(notification);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Notification>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Notification sent successfully")
                        .data(saved)
                        .build()
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Notification>>> getNotificationsByUserId(
            @PathVariable String userId) {

        List<Notification> notifications =
                notificationService.getNotificationsByUserId(userId);

        return ResponseEntity.ok(
                ApiResponse.<List<Notification>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Notifications fetched successfully")
                        .data(notifications)
                        .build()
        );
    }
}
