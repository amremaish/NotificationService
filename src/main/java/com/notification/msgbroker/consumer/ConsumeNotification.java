package com.notification.msgbroker.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.notification.msgbroker.config.MessagingConfig;
import com.notification.msgbroker.dto.NotificationQueueItem;
import com.notification.service.NotificationService;

@Component
public class ConsumeNotification {
	@Autowired
	private NotificationService notificationService;

	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(NotificationQueueItem notificationQueue) {
		if (notificationQueue.getCustomer_id() != 0) {
			notificationService.SendNotifyToCustomer(notificationQueue);
		} else {
			notificationService.SendNotifyToGroup(notificationQueue);
		}
	}
}
