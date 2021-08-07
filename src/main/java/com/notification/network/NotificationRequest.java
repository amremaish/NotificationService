package com.notification.network;

import com.notification.dao.entites.Customer;
import com.notification.dao.entites.Device;
import com.notification.dao.entites.Notification;
import com.notification.msgbroker.dto.NotificationQueueItem;

public class NotificationRequest {

	public static boolean sendNotification(NotificationQueueItem notificationQueue, Customer customer,
			Device customerDevice) {
		// here we send actual notification
		return true;
	}

	public static boolean sendSMS(NotificationQueueItem notificationQueue, Customer customer, Device customerDevice) {
		// here we send actual SMS
		return true;
	}

	public static boolean sendRequestToCustomer(NotificationQueueItem notificationQueue, Customer customer,
			Device customerDevice) {
		if (notificationQueue.getType().equals(Notification.NOTIFY_TYPE)) {
			return sendNotification(notificationQueue, customer, customerDevice);
		} else {
			return sendSMS(notificationQueue, customer, customerDevice);
		}
	}

}
