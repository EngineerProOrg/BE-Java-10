package com.engineerpro.firstexample.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

  private final NotificationService notificationService;

  public NotificationController(@Qualifier("smsNotificationService") NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @GetMapping("/send")
  public String sendNotification(@RequestParam String message) {
    notificationService.sendNotification(message);
    return "Notification sent!";
  }
}
