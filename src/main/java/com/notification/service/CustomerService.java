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
import com.notification.dao.repo.CustomerRepo;
import com.notification.error.CustomException;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo customerRepo;

	public Customer findbyId(long id) {
		return customerRepo.findById(id).orElseThrow(() -> new CustomException("Customer is not found"));
	}

	public Map<String, Object> findAll(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Customer> pagedResult = customerRepo.findAll(paging);
		List<Customer> cutomers;
		if (pagedResult.hasContent()) {
			cutomers = pagedResult.getContent();
		} else {
			cutomers = new ArrayList<Customer>();
		}

		Map<String, Object> response = new HashMap<>();
		response.put("customers", cutomers);
		response.put("pageNumber", pageNo);
		response.put("totalPages",(int) Math.ceil(customerRepo.count() / Double.valueOf(pageSize)));
		return response;
	}
	@Transactional
	public Customer updateCustomer(Customer cutomerParams) {
		Customer CostomerDB = customerRepo.findById(cutomerParams.getId()).orElseThrow(() -> new CustomException("Customer is not found"));
		CostomerDB.setEmail(cutomerParams.getEmail());
		CostomerDB.setUsername(cutomerParams.getUsername());
		CostomerDB.setPhone_number(cutomerParams.getPhone_number());
		return customerRepo.save(CostomerDB);
	}
	
	public Customer createCustomer(Customer cutomerParams) {
		Customer Customer = customerRepo.findByEmail(cutomerParams.getEmail()).orElse(null);
		if (Customer != null) {
			throw  new CustomException("Customer is already existing");
		}
		return customerRepo.save(cutomerParams);
	}
	

}
