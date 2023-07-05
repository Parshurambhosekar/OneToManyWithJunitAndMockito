package com.parshuram.onetomanymapping.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.parshuram.onetomanymapping.entity.ApiResponse;
import com.parshuram.onetomanymapping.entity.BankAccount;
import com.parshuram.onetomanymapping.entity.User;
import com.parshuram.onetomanymapping.serviceImpl.BankAccountImpl;

@SpringBootTest
public class BankAccountTest {

	@Mock
	private BankAccountImpl accountImpl;

	@InjectMocks
	private BankAccountController bankAccountController;

	@Test
	void testCreateBankAccount() {

		BankAccount bankAccount = new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l));

		when(accountImpl.createBankAccount(bankAccount, 1)).thenReturn(bankAccount);

		ResponseEntity<BankAccount> responseEntity = bankAccountController.createBankAccount(bankAccount, 1);

		assertEquals(bankAccount, responseEntity.getBody());

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		assertEquals(201, responseEntity.getStatusCodeValue());
	}

	@Test
	void testUpdateBankAccount() {

		String accountId = "abc123";

		BankAccount bankAccount = new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l));

		when(accountImpl.updateBankAccount(bankAccount, accountId)).thenReturn(bankAccount);

		ResponseEntity<BankAccount> responseEntity = bankAccountController.updateBankAccount(bankAccount,
				bankAccount.getAccountId());

		assertEquals(accountId, responseEntity.getBody().getAccountId());

		assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());

		assertEquals(202, responseEntity.getStatusCodeValue());

		assertEquals("SBI", responseEntity.getBody().getBankName());

		assertEquals("Saving", responseEntity.getBody().getAccountType());

		assertEquals(16000l, responseEntity.getBody().getBalance());

		assertEquals("Parshuram Bhosekar", responseEntity.getBody().getUser().getName());

		assertEquals("Sangli", responseEntity.getBody().getUser().getCity());

		assertEquals(9623771726l, responseEntity.getBody().getUser().getMobileNumber());

		assertEquals("p@gmail.com", responseEntity.getBody().getUser().getEmail());

		assertEquals(1, responseEntity.getBody().getUser().getUserId());
	}

	@Test
	void testGetListOfBankAccountDetails() {

		List<BankAccount> listOfBankAccount = new ArrayList<>();
		listOfBankAccount.add(new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l)));
		listOfBankAccount.add(new BankAccount(2, "xyz456", "AXIS", "Saving", 22000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l)));
		listOfBankAccount.add(new BankAccount(3, "abc123", "HDFC", "Current", 6000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l)));
		listOfBankAccount.add(new BankAccount(4, "abc123", "SBI", "Saving", 45000l,
				new User(2, "Sumit Choudhari", "Sangli", "s@gmailc.com", 9307304360l)));

		when(accountImpl.getAllBankAccounts()).thenReturn(listOfBankAccount);

		ResponseEntity<List<BankAccount>> responseEntity = bankAccountController.listofAllAccounts();

		assertEquals(4, responseEntity.getBody().size());

		assertEquals(listOfBankAccount, responseEntity.getBody());

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	void testDeleteAccount() {

		String accountId="abc123";
		
		BankAccount bankAccount = new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l));

		accountImpl.deleteBankAccount(bankAccount.getAccountId());
		
		ResponseEntity<ApiResponse> responseEntity = bankAccountController.deleteAccount(accountId);
		
		assertEquals("Account deleted Successfully....", responseEntity.getBody().getMessage());
		
		assertEquals(true, responseEntity.getBody().getSuccess());
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	void testGetAccountById() {
		
		BankAccount bankAccount = new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l));
		
		when(accountImpl.getAccountById("abc123")).thenReturn(bankAccount);
		
		 ResponseEntity<BankAccount> responseEntity = bankAccountController.getAccountById("abc123");
		 
		 assertEquals("abc123", responseEntity.getBody().getAccountId());
		 
		 assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		 assertEquals(200, responseEntity.getStatusCodeValue());
		 
		 assertEquals("SBI", responseEntity.getBody().getBankName());
	}
	
	@Test
	void testGetAccountByType() {
		
		List<BankAccount> listOfBankAccount = new ArrayList<>();
		listOfBankAccount.add(new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l)));
		listOfBankAccount.add(new BankAccount(2, "xyz456", "AXIS", "Saving", 22000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l)));
		listOfBankAccount.add(new BankAccount(3, "abc123", "HDFC", "Current", 6000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l)));
		listOfBankAccount.add(new BankAccount(4, "abc123", "SBI", "Saving", 45000l,
				new User(2, "Sumit Choudhari", "Sangli", "s@gmailc.com", 9307304360l)));

		when(accountImpl.getAccountByType("Saving")).thenReturn(listOfBankAccount);
		
		ResponseEntity<List<BankAccount>> responseEntity = bankAccountController.getAccountByType("Saving");
		
		List<BankAccount> list = responseEntity.getBody().stream()
				.filter(bank->bank.getAccountType().equalsIgnoreCase("saving"))
				.collect(Collectors.toList());
		
		assertEquals(3, list.size());
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

}
