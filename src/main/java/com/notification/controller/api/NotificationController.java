package com.notification.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.dao.entites.Notification;
import com.notification.dao.models.NotificationModel;
import com.notification.error.CustomResponse;
import com.notification.service.NotificationService;

@RestController
@RequestMapping(value = "/api/v1/notification")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	
	@PostMapping("/send")
	public Object sendNotification(@RequestBody NotificationModel notificationParams) {	
		return new CustomResponse(notificationService.sendNotification(notificationParams));
	}
}
