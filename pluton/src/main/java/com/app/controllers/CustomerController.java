package com.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CustomerDTO;
import com.app.dto.SigninRequest;
import com.app.dto.SigninResponse;
import com.app.security.JwtUtils;
import com.app.service.CustomerService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:3000")
@Validated
public class CustomerController {
	
	@Autowired
	private CustomerService custService;
	
	@Autowired
	private JwtUtils utils;

	@Autowired
	private AuthenticationManager mgr;
	
	// Endpoint to retrieve all customers
    // URL: http://localhost:8080/customer/all
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> listAllCustomers() {
	    try {
	        List<CustomerDTO> customers = custService.getAllCustomers();
	        return ResponseEntity.ok(customers);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
	    }
	}

    // Endpoint to retrieve details of a specific customer
    // URL: http://localhost:8080/customer/{custId}
    @GetMapping("/{custId}")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
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
    @PostMapping("/register")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO custDto) {
        //try {
            CustomerDTO createdCustomer = custService.addCustomer(custDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        //}
//        catch (CustomerServiceException e) {
//            throw new CustomerControllerException("Error while adding customer: " + e.getMessage());
//        }
    }

    // Endpoint for customer login
    // URL: http://localhost:8080/customer/login
//    @PostMapping("/signin")
//    public ResponseEntity<CustomerDTO> loginCustomer(@RequestParam String email, @RequestParam String password) {
//        CustomerDTO customer = custService.loginCustomer(email, password);
//        if (customer != null) {
//            return ResponseEntity.ok(customer);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
    
    @PostMapping("/signin")
	public ResponseEntity<?> signinUser(@RequestBody @Valid SigninRequest reqDTO) {
    	try {
            // Perform user authentication
            Authentication verifiedAuth = mgr.authenticate(
                    new UsernamePasswordAuthenticationToken(reqDTO.getEmail(), reqDTO.getPassword()));

            // If authentication is successful, return a JWT token
            if (verifiedAuth.isAuthenticated()) {
                return ResponseEntity.ok(new SigninResponse(utils.generateJwtToken(verifiedAuth), "Successful Authentication!!!"));
            } else {
                // If authentication fails, return a 401 Unauthorized status code
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user credentials");
            }
        } catch (AuthenticationException e) {
        	
        	System.out.println("inside signin exception");
            // If an AuthenticationException occurs (e.g., invalid credentials), return a 401 Unauthorized status code
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user credentials");
        }
	}

    // Endpoint to edit customer details
    // URL: http://localhost:8080/customer/{custId}
    @PutMapping("/{custId}")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<List<CustomerDTO>> getCustomersSortedByRegistrationDateAsc() {
        List<CustomerDTO> customers = custService.getCustomersSortedByRegistrationDateAsc();
        return ResponseEntity.ok(customers);
    }

    // Endpoint to retrieve customers sorted by registration date in descending order
    // URL: http://localhost:8080/customer/sortByRegistration/desc
    @GetMapping("/sortByRegistration/desc")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<List<CustomerDTO>> getCustomersSortedByRegistrationDateDesc() {
        List<CustomerDTO> customers = custService.getCustomersSortedByRegistrationDateDesc();
        return ResponseEntity.ok(customers);
    }
	
	
}
