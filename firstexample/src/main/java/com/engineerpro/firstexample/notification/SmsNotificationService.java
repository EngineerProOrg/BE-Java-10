package com.engineerpro.firstexample.notification;

import org.springframework.stereotype.Service;

@Service("smsNotificationService")
public class SmsNotificationService implements NotificationService {

  @Override
  public void sendNotification(String message) {
    System.out.println("Sending SMS Notification with message: " + message);
  }
}
