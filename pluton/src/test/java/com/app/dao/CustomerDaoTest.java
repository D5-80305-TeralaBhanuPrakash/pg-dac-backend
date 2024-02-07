package com.app.dao;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Customer;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class CustomerDaoTest {

		@Autowired
		private CustomerDao custDao;
		
		@Test
		void testSaveCustomers() {
			
//	                custDao.save(new Customer(1,"John", "Doe", LocalDate.parse("2000-01-01"), "Male", "john.doe@example.com", "1234567890", LocalDate.now(),true));
//	                custDao.save(new Customer(2,"Jane", "Smith", LocalDate.parse("1995-02-02"), "Female", "jane.smith@example.com", "2345678901", LocalDate.now(),true));
	                // Add more customers here as needed
	        
		}
}
