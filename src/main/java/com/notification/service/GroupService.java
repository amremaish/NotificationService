package com.notification.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.notification.dao.entites.Customer;
import com.notification.dao.entites.Group;
import com.notification.dao.entites.GroupCustomer;
import com.notification.dao.repo.CustomerRepo;
import com.notification.dao.repo.GroupCustomerRepo;
import com.notification.dao.repo.GroupRepo;
import com.notification.error.CustomException;

@Service
public class GroupService {
	@Autowired
	private GroupRepo groupRepo;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private GroupCustomerRepo groupCustomerRepo;
	@Autowired
	private CustomerRepo customerRepo;

	public Group findbyId(long id) {
		return groupRepo.findById(id).orElseThrow(() -> new CustomException("group is not found"));
	}

	public Map<String, Object> findAll(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Group> pagedResult = groupRepo.findAll(paging);
		List<Group> groups;
		if (pagedResult.hasContent()) {
			groups = pagedResult.getContent();
		} else {
			groups = new ArrayList<Group>();
		}

		Map<String, Object> response = new HashMap<>();
		response.put("groups", groups);
		response.put("pageNumber", pageNo);
		response.put("totalPages", (int) Math.ceil(groupRepo.count() / Double.valueOf(pageSize)));
		return response;
	}

	@Transactional
	public Group updateGroup(Group groupParams) {
		Group groupDB = groupRepo.findById(groupParams.getId())
				.orElseThrow(() -> new CustomException("group is not found"));
		groupDB.setName(groupParams.getName());
		return groupRepo.save(groupDB);
	}

	public Group createGroup(Group groupParams) {
		Group group = groupRepo.findByName(groupParams.getName()).orElse(null);
		if (group != null) {
			throw new CustomException("group is already existing");
		}
		return groupRepo.save(groupParams);
	}

	public Object addCustomerToGroup(Customer customerParams, long group_id) {
		Group group = groupRepo.findById(group_id).orElseThrow(() -> new CustomException("group is not found"));
		Customer customer = null;
		if (customerParams.getId() == 0) {
			customer = customerService.createCustomer(customerParams);
		} else {
			customer = customerService.findbyId(customerParams.getId());
		}
		GroupCustomer groupCustomer = new GroupCustomer();
		groupCustomer.setGroup(group);
		groupCustomer.setCustomer(customer);
		groupCustomerRepo.save(groupCustomer);
		return "successfully added";
	}

	public Object getCustomersGroup(int pageNo, int pageSize, long group_id) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		List<Customer> pagedResult = customerRepo.getCustomersGroup(group_id, paging);
		Map<String, Object> response = new HashMap<>();
		response.put("customers", pagedResult);
		response.put("pageNumber", pageNo);
		response.put("totalPages", (int) Math.ceil(groupRepo.count() / Double.valueOf(pageSize)));
		return response;
	}

	public List<Customer> getCustomersGroup(long group_id) {
		return customerRepo.getCustomersGroup(group_id);
	}
}
