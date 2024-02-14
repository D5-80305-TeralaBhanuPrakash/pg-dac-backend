package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.CustomerAlreadyRegisteredException;
import com.app.custom_exceptions.CustomerServiceException;
import com.app.dao.CustomerDao;
import com.app.dto.CustomerDTO;
import com.app.entities.Customer;
import com.app.entities.Role;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao custDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public List<CustomerDTO> getAllCustomers() {
		// TODO Auto-generated method stub
		return custDao.findAll().stream().map(cust->mapper.map(cust, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO addCustomer(CustomerDTO custDto) {
	    try {
	    	Optional<Customer> exisCust = custDao.findByEmail(custDto.getEmail());
	    	if(exisCust.isPresent()) {
	    		throw new CustomerAlreadyRegisteredException("customer already registered");
	    	}
	    	custDto.setRole(Role.ROLE_USER);
	        custDto.setRegistrationDate(LocalDate.now());
	        custDto.setStatus(true);
	        Customer cust = custDao.save(mapper.map(custDto, Customer.class));
	        cust.setPassword(encoder.encode(custDto.getPassword()));
	        return mapper.map(cust, CustomerDTO.class);
	    } catch (Exception e) {
	        throw new CustomerServiceException("Error while adding customer: " + e.getMessage());
	    }
	}

	@Override
	public CustomerDTO getCustomerDetails(Integer custId) {
		Customer cust = custDao.findById(custId).orElseThrow();
		// TODO Auto-generated method stub
		return mapper.map(cust, CustomerDTO.class);
	}

	@Override
	public CustomerDTO loginCustomer(String email, String password) {
		Customer cust = custDao.findCustomerByEmailAndPassword(email,password);
		return mapper.map(cust, CustomerDTO.class);
	}

	@Override
	public CustomerDTO editCustomer(Integer custId, CustomerDTO custDto) {
	    // Find the customer by ID or throw EntityNotFoundException
	    Customer cust = custDao.findById(custId)
	                          .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + custId));

	    // Map the fields from CustomerDTO to Customer
	    // Assuming 'mapper' is a properly configured mapper object
	    mapper.map(custDto, cust);

	    // Set the customer ID (assuming it's not part of the DTO)
	    cust.setCustomerId(custId);

	    // Save the updated customer and return its DTO representation
	    return mapper.map(custDao.save(cust), CustomerDTO.class);
	}

	@Override
	public String deleteCustomer(Integer custId) {
		Customer cust = custDao.findById(custId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + custId));
		custDao.delete(cust);
		return "Deleted Successfully";
	}

	@Override
	public List<CustomerDTO> getCustomersSortedByRegistrationDateAsc() {
		List<Customer> custList = custDao.findAllByOrderByRegistrationDateAsc();
		return custList.stream()
				.map(cust -> mapper.map(cust, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<CustomerDTO> getCustomersSortedByRegistrationDateDesc() {
		List<Customer> custList = custDao.findAllByOrderByRegistrationDateDesc();
		return custList.stream()
				.map(cust -> mapper.map(cust, CustomerDTO.class))
				.collect(Collectors.toList());
		
	}

}
