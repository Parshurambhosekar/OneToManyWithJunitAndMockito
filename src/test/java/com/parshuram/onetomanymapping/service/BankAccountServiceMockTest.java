package com.parshuram.onetomanymapping.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.parshuram.onetomanymapping.entity.BankAccount;
import com.parshuram.onetomanymapping.entity.User;
import com.parshuram.onetomanymapping.exception.ResourceNotFoundExcepton;
import com.parshuram.onetomanymapping.repository.BankAccountRepository;
import com.parshuram.onetomanymapping.repository.UserRepository;
import com.parshuram.onetomanymapping.serviceImpl.BankAccountImpl;

@SpringBootTest
public class BankAccountServiceMockTest {

	@Mock
	private BankAccountRepository accountRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private BankAccountImpl bankAccountImpl;
	
	private User user1;
	private User user2;
	
	private BankAccount bankAccount1;
	private BankAccount bankAccount2;
	private BankAccount bankAccount3;
	private List<BankAccount> bankAccountList=new ArrayList<>();

	@BeforeEach
	void init() {

		user1 = new User(1, "Parshuram Bhosekar", "Pune", "p@gmail.com", 9623771726l);

		bankAccount1 = new BankAccount();
		bankAccount1.setAccountId("abc123");
		bankAccount1.setAccountType("Saving");
		bankAccount1.setBalance(16000l);
		bankAccount1.setBankName("SBI");
		bankAccount1.setId(1);
		bankAccount1.setUser(user1);
		
		bankAccount3 = new BankAccount();
		bankAccount3.setAccountId("xyz123");
		bankAccount3.setAccountType("Saving");
		bankAccount3.setBalance(22000l);
		bankAccount3.setBankName("AXIS");
		bankAccount3.setId(2);
		bankAccount3.setUser(user1);
		
		user2 = new User(2, "Sumit Pawar", "Pune", "s@gmail.com", 9623771745l);
		
		bankAccount2 = new BankAccount();
		bankAccount2.setAccountId("pqr456");
		bankAccount2.setAccountType("Current");
		bankAccount2.setBalance(16000l);
		bankAccount2.setBankName("SBI");
		bankAccount2.setId(1);
		bankAccount2.setUser(user2);

	}

	@Test
	void testCreateBankAccount() {

		when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user1));

		Mockito.when(accountRepository.save(bankAccount1)).thenReturn(bankAccount1);

		BankAccount createBankAccount = bankAccountImpl.createBankAccount(bankAccount1, 1);

		assertNotNull(createBankAccount);

		assertThat(createBankAccount.getBankName()).isEqualTo("SBI");

		assertEquals(bankAccount1, createBankAccount);

	}
	
	@Test
	void testUpdateBankAccount() {
		
		when(accountRepository.findByaccountId("abc123")).thenReturn(bankAccount1);
		
		when(accountRepository.save(bankAccount1)).thenReturn(bankAccount1);
		bankAccount1.setAccountType("Current");
		
		BankAccount updateBankAccount = bankAccountImpl.updateBankAccount(bankAccount1, "abc123");
		
		assertNotNull(updateBankAccount);
		
		assertThat(updateBankAccount.getAccountType()).isEqualTo("Current");
		
		assertEquals("Current", updateBankAccount.getAccountType());	
	}
	
	@Test
	void testGetAllBankAccountDetails() {
		
		bankAccountList.add(bankAccount1);
		bankAccountList.add(bankAccount2);
		 
		when(accountRepository.findAll()).thenReturn(bankAccountList);
		
		List<BankAccount> allBankAccounts = bankAccountImpl.getAllBankAccounts();
		
		assertNotNull(bankAccountList);
		
		assertEquals(2, allBankAccounts.size());	
	}
	
	@Test
	void testDeleteBankAccount() {
		
		when(accountRepository.findByaccountId(bankAccount3.getAccountId())).thenReturn(bankAccount3);
		
		doNothing().when(accountRepository).delete(bankAccount3);
		
		bankAccountImpl.deleteBankAccount("xyz123");
		
		verify(accountRepository,times(1)).delete(bankAccount3);
	}
	
	@Test
	void testGetAccountById() {
		
		when(accountRepository.findByaccountId(bankAccount2.getAccountId())).thenReturn(bankAccount2);
		
		BankAccount accountById = bankAccountImpl.getAccountById("pqr456");
		
		assertNotNull(accountById);
		
		assertThat(accountById.getAccountId()).isEqualTo("pqr456");
		
		assertEquals(bankAccount2.getAccountId(), accountById.getAccountId());
	}
	
	@Test
	void testGetAccountByIdForException() {
		
		when(accountRepository.findByaccountId("mno123")).thenReturn(bankAccount1);

		assertThrows(ResourceNotFoundExcepton.class, ()->{
			bankAccountImpl.getAccountById(bankAccount1.getAccountId());
		});
	}
	
	@Test
	void testGetAccountByName() {
		
		bankAccountList.add(bankAccount1);
		bankAccountList.add(bankAccount2);
		
		when(accountRepository.findAll()).thenReturn(bankAccountList);
		
		BankAccount accountByBankName = bankAccountImpl.getAccountByBankName("SBI");
		
		assertNotNull(accountByBankName);
		
		assertEquals("SBI", accountByBankName.getBankName());
		
		assertThat(accountByBankName.getBankName()).isEqualToIgnoringCase("sbi");
		
	}
	
	@Test
	void testGetBankAccountByType() {
		
		bankAccountList.add(bankAccount1);
		bankAccountList.add(bankAccount2);
		bankAccountList.add(bankAccount3);
		
		when(accountRepository.findAll()).thenReturn(bankAccountList);
		
		List<BankAccount> accountByType = bankAccountImpl.getAccountByType("Saving");
		
		assertThat(accountByType.size()).isEqualTo(2);

		assertNotNull(accountByType);
		
		assertEquals(2, accountByType.size());
	}
	
	@Test
	void testGetAccountByNameException() {
		
		bankAccountList.add(bankAccount1);
		bankAccountList.add(bankAccount2);
		
		Mockito.when(accountRepository.findAll()).thenReturn(bankAccountList);
		
		assertThrows(ResourceNotFoundExcepton.class, ()->{
			bankAccountImpl.getAccountByBankName("ICICI").getBankName();
		});
		
	}
	
	
	
	
	
	

}
