package com.notification.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notification.dao.entites.Customer;
import com.notification.dao.entites.Group;
import com.notification.error.CustomResponse;
import com.notification.service.GroupService;

@RestController
@RequestMapping(value = "/api/v1/group")
public class GroupController {
	@Autowired
	private GroupService groupService;

	@PostMapping("/create")
	public Object createGroup(@RequestBody Group groupParams) {
		return new CustomResponse(groupService.createGroup(groupParams));
	}

	@PostMapping("/update")
	public Object updateGroup(@RequestBody Group groupParams) {
		return new CustomResponse(groupService.updateGroup(groupParams));
	}

	@PostMapping("/{group_id}/add_customer")
	public Object addCustomer(@PathVariable long group_id, @RequestBody Customer customerParams) {
		return new CustomResponse(groupService.addCustomerToGroup(customerParams, group_id));
	}
	
	@GetMapping("/{group_id}/customers")
	public Object getCustomersGroup(@PathVariable long group_id , @RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) {
		return new CustomResponse(groupService.getCustomersGroup(pageNo, pageSize,group_id));
	}
	
	@GetMapping("/all")
	public Object getGroups(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) {
		return new CustomResponse(groupService.findAll(pageNo, pageSize));
	}
}
