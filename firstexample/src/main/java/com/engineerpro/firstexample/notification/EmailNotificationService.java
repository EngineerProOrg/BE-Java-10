package com.engineerpro.firstexample.notification;

import org.springframework.stereotype.Service;

@Service("emailNotificationService")
public class EmailNotificationService implements NotificationService {

  @Override
  public void sendNotification(String message) {
    System.out.println("Sending Email Notification with message: " + message);
  }
}
