package com.app.service;

import java.util.List;

import com.app.dto.TransactionDTO;

public interface TransactionService {

	TransactionDTO addTrasactionsOfCustomer(Integer custId, TransactionDTO trasacDto);

	List<TransactionDTO> getAllTransactions();

	List<TransactionDTO> getAllTransactionOfCustomer(Integer custId);

}
