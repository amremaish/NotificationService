package com.notification.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.notification.dao.entites.Customer;
import com.notification.dao.entites.Device;
import com.notification.dao.entites.Notification;
import com.notification.dao.models.NotificationModel;
import com.notification.dao.repo.NotificationRepo;
import com.notification.error.CustomException;
import com.notification.msgbroker.config.MessagingConfig;
import com.notification.msgbroker.dto.NotificationQueueItem;
import com.notification.network.NotificationRequest;

@Service
public class NotificationService {
	@Autowired
	private NotificationRepo notificationRepo;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private RabbitTemplate template;

	public Object sendNotification(NotificationModel notificationParams) {

		if (notificationParams.getGroup_id() == 0 && notificationParams.getCustomer_id() == 0) {
			throw new CustomException("You should privide group_id or customer_id");
		} else if (!notificationParams.getType().equals(Notification.SMS_TYPE)
				&& !notificationParams.getType().equals(Notification.NOTIFY_TYPE)) {
			throw new CustomException("We support only sms and noity type");
		}

		for (Entry<String, String> temp : notificationParams.getTemplates().entrySet()) {
			String language = temp.getKey();
			String msg = this.replaceKeyInMessage(language, temp.getValue(), notificationParams.getParams());
			if (msg == null) {
				throw new CustomException("There is an error in your template with language " + language);
			}
			// push to queue
			pushToQueue(msg, language, notificationParams);
		}
		return "successfully Sent";
	}

	private void pushToQueue(String message, String language, NotificationModel model) {
		NotificationQueueItem nq = new NotificationQueueItem();
		nq.setGroup_id(model.getGroup_id());
		nq.setCustomer_id(model.getCustomer_id());
		nq.setType(model.getType());
		nq.setMessage(message);
		nq.setLanguage(language);
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, nq);
	}

	private String replaceKeyInMessage(String language, String msg, Map<String, String> paramsMap) {
		int count = 0;
		long countDel = StringUtils.countOccurrencesOf(msg, "%");
		if (countDel % 2 != 0) {
			return null;
		}
		for (Entry<String, String> params : paramsMap.entrySet()) {
			if (params.getKey().endsWith(language)) {
				String paramKey = "%" + params.getKey().split("_")[0] + "%";
				String paramValue = params.getValue();
				msg = msg.replaceAll(paramKey, paramValue);
				count++;
			}
		}

		if (countDel / 2 == count) {
			return msg;
		}
		return null;
	}

	public void SendNotifyToCustomer(NotificationQueueItem notificationQueue) {

		Customer customer = customerService.findbyId(notificationQueue.getCustomer_id());
		if (!notificationQueue.getLanguage().equals(customer.getLanguage())) {
			return;
		}
		List<Device> devices = deviceService.findAll(notificationQueue.getCustomer_id());
		for (int i = 0; i < devices.size(); i++) {
			boolean isServed = NotificationRequest.sendRequestToCustomer(notificationQueue, customer, devices.get(i));
			Notification notify = new Notification();
			notify.setCustomer_id(notificationQueue.getCustomer_id());
			notify.setDevice_id(devices.get(i).getId());
			notify.setLanguage(customer.getLanguage());
			notify.setServed(isServed);
			notify.setType(notificationQueue.getType());
			notificationRepo.save(notify);
		}

	}

	public void SendNotifyToGroup(NotificationQueueItem notificationQueue) {
		List<Customer> customers = groupService.getCustomersGroup(notificationQueue.getGroup_id());
		for (Customer customer : customers) {
			notificationQueue.setCustomer_id(customer.getId());
			this.SendNotifyToCustomer(notificationQueue);
		}

	}

}
