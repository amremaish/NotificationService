package com.notification.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.dao.entites.UserEntity;
import com.notification.dao.repo.UserRepo;

@RestController
@RequestMapping(value = "/api/v1/")
public class NotificationController {

	@Autowired
	UserRepo r ;
	@GetMapping("id")
	public Object ViewAdditional() {
		UserEntity u = new UserEntity();
		u.setEmail("asdasd");
		u.setFirst_name("laksd");
		r.save(u);
		return u;
	}
}
