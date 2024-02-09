package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerDTO;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins="http://localhost:3000")
public class CustomerController {
	@Autowired
	private CustomerService custService;
	
	
	public CustomerController() {
		
	}
	
	// Endpoint to retrieve all customers
    // URL: http://localhost:8080/customer/all
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> listAllCustomers() {
        List<CustomerDTO> customers = custService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Endpoint to retrieve details of a specific customer
    // URL: http://localhost:8080/customer/{custId}
    @GetMapping("/{custId}")
    public ResponseEntity<CustomerDTO> getCustomerDetails(@PathVariable Integer custId) {
        CustomerDTO customer = custService.getCustomerDetails(custId);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to add a new customer
    // URL: http://localhost:8080/customer
    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO custDto) {
        CustomerDTO createdCustomer = custService.addCustomer(custDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    // Endpoint for customer login
    // URL: http://localhost:8080/customer/login
    @PostMapping("/login")
    public ResponseEntity<CustomerDTO> loginCustomer(@RequestParam String email, @RequestParam String password) {
        CustomerDTO customer = custService.loginCustomer(email, password);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Endpoint to edit customer details
    // URL: http://localhost:8080/customer/{custId}
    @PutMapping("/{custId}")
    public ResponseEntity<CustomerDTO> editCustomer(@PathVariable Integer custId, @RequestBody CustomerDTO custDto) {
        CustomerDTO updatedCustomer = custService.editCustomer(custId, custDto);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete a customer
    // URL: http://localhost:8080/customer/{custId}
    @DeleteMapping("/{custId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer custId) {
        String result = custService.deleteCustomer(custId);
        if (result.equals("deleted")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    // Endpoint to retrieve customers sorted by registration date in ascending order
    // URL: http://localhost:8080/customer/sortByRegistration/asc
    @GetMapping("/sortByRegistration/asc")
    public ResponseEntity<List<CustomerDTO>> getCustomersSortedByRegistrationDateAsc() {
        List<CustomerDTO> customers = custService.getCustomersSortedByRegistrationDateAsc();
        return ResponseEntity.ok(customers);
    }

    // Endpoint to retrieve customers sorted by registration date in descending order
    // URL: http://localhost:8080/customer/sortByRegistration/desc
    @GetMapping("/sortByRegistration/desc")
    public ResponseEntity<List<CustomerDTO>> getCustomersSortedByRegistrationDateDesc() {
        List<CustomerDTO> customers = custService.getCustomersSortedByRegistrationDateDesc();
        return ResponseEntity.ok(customers);
    }
	
	
}
