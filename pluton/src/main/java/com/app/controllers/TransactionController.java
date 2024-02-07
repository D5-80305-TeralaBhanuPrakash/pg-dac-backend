package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.TransactionDTO;
import com.app.service.TransactionService;

@RestController
@RequestMapping("/customer/transaction")
@CrossOrigin(origins="http://localhost:3000")
@Validated
public class TransactionController {

	@Autowired
	private TransactionService transacService;
	
	//Method to get transactions of all the customers
	@GetMapping
	public List<TransactionDTO> getAllTransactions(){
		return transacService.getAllTransactions();
	}
	
	//Method to get all transactions of a specific customer
	@GetMapping("custom/{custId}")
	public List<TransactionDTO> getAllTransactionOfCustomer(@PathVariable Integer custId){
		return transacService.getAllTransactionOfCustomer(custId);
	}
	
	@GetMapping("appl/{applId}")
	public List<TransactionDTO> getAllTransactionOfLoan(@PathVariable Integer applId){
		return transacService.getAllTransactionOfLoan(applId);
	}
	
	
	@PostMapping("/{custId}")
	public TransactionDTO addTrasactionsOfCustomer(@PathVariable Integer custId,@RequestBody TransactionDTO trasacDto){
		return transacService.addTrasactionsOfCustomer(custId,trasacDto);
	}
	
	
	
}
