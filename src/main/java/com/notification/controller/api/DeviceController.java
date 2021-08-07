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
import com.notification.dao.entites.Device;
import com.notification.dao.entites.Group;
import com.notification.error.CustomResponse;
import com.notification.service.DeviceService;
import com.notification.service.GroupService;

@RestController
@RequestMapping(value = "/api/v1/device")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;

	
	@PostMapping("/{customer_id}/create")
	public Object createDevice(@PathVariable long customer_id ,@RequestBody Device deviceParams ) {
		return new CustomResponse(deviceService.createDevice(deviceParams,customer_id));
	}
	
	@GetMapping("/{customer_id}/all")
	public Object getDevices(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize , @PathVariable long customer_id) {
		return new CustomResponse(deviceService.findAll(pageNo, pageSize, customer_id));
	}
}
