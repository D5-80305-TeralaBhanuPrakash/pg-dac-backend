package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.TransactionService;

@RestController
@RequestMapping("/customer/transaction")
@CrossOrigin(origins="http://localhost:3000")
@Validated
public class TransactionController {

	@Autowired
	private TransactionService transacService;
	
	
//	@PostMapping("/{custId}")
	
}
