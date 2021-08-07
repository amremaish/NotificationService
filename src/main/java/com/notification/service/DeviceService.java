package com.notification.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.notification.dao.entites.Customer;
import com.notification.dao.entites.Device;
import com.notification.dao.repo.DeviceRepo;
import com.notification.error.CustomException;

@Service
public class DeviceService {
	@Autowired
	private DeviceRepo deviceRepo;
	@Autowired
	private CustomerService customerService;

	public Map<String, Object> findAll(Integer pageNo, Integer pageSize, long customer_id) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		List<Device> pagedResult = deviceRepo.getCustomerDevices(customer_id, paging);
		Map<String, Object> response = new HashMap<>();
		response.put("devices", pagedResult);
		response.put("pageNumber", pageNo);
		response.put("totalPages", (int) Math.ceil(deviceRepo.count() / Double.valueOf(pageSize)));
		return response;
	}
	
	public List<Device> findAll(long customer_id) {
		return deviceRepo.getCustomerDevices(customer_id);
	}
	
	public Device createDevice(Device deviceParams, long customer_id) {
		if (!deviceParams.getDeviceType().equals(Device.ANDROID_TYPE)
				&& !deviceParams.getDeviceType().equals(Device.iOS_TYPE)) {
			throw new CustomException("invalid device type");
		}
		Customer customer = customerService.findbyId(customer_id);
		deviceParams.setCustomer(customer);
		return deviceRepo.save(deviceParams);
	}

}
