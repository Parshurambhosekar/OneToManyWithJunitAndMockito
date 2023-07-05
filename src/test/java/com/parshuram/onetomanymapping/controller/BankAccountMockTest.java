package com.parshuram.onetomanymapping.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parshuram.onetomanymapping.entity.BankAccount;
import com.parshuram.onetomanymapping.entity.User;
import com.parshuram.onetomanymapping.service.BankAccountService;

@WebMvcTest(value = BankAccountController.class)
public class BankAccountMockTest {

	@MockBean
	private BankAccountService accountService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testCreateBankAccount() throws Exception {

		BankAccount bankAccount = new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l));

		when(accountService.createBankAccount(bankAccount, 1)).thenReturn(bankAccount);

		ObjectMapper mapper = new ObjectMapper();
		String valueAsString = mapper.writeValueAsString(bankAccount);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/bankAccount/user/1/account/")
				.contentType(MediaType.APPLICATION_JSON).content(valueAsString);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		int status = response.getStatus();

		assertEquals(201, status);

		assertEquals(valueAsString, response.getContentAsString());

		assertEquals("application/json", response.getContentType());
	}

	@Test
	void testUpdateBackAccountDetails() throws Exception {

		BankAccount bankAccount = new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l));

		when(accountService.updateBankAccount(bankAccount, "abc123")).thenReturn(bankAccount);

		ObjectMapper mapper = new ObjectMapper();
		String valueAsString = mapper.writeValueAsString(bankAccount);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/bankAccount/{id}", "abc123")
				.contentType(MediaType.APPLICATION_JSON).content(valueAsString);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		int status = response.getStatus();

		assertEquals(202, status);

		assertEquals(valueAsString, response.getContentAsString());

		assertEquals("application/json", response.getContentType());
	}

	@Test
	void testListOfAllAccounts() throws Exception {

		List<BankAccount> listOfBankAccount = new ArrayList<>();
		listOfBankAccount.add(new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l)));
		listOfBankAccount.add(new BankAccount(2, "xyz456", "AXIS", "Saving", 22000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l)));
		listOfBankAccount.add(new BankAccount(3, "abc123", "HDFC", "Current", 6000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l)));
		listOfBankAccount.add(new BankAccount(4, "abc123", "SBI", "Saving", 45000l,
				new User(1, "Sumit Choudhari", "Sangli", "s@gmailc.com", 9307304360l)));

		when(accountService.getAllBankAccounts()).thenReturn(listOfBankAccount);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bankAccount/")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		int status = response.getStatus();

		assertEquals(200, status);

		assertEquals("application/json", response.getContentType());
	}

	@Test
	void testDeleteBankAccount() throws Exception {

		BankAccount bankAccount = new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l));

		accountService.deleteBankAccount(bankAccount.getAccountId());

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/bankAccount/{id}", "abc123");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		int status = response.getStatus();

		assertEquals(200, status);
	}

	@Test
	void testGetAccountByName() throws Exception {

		BankAccount bankAccount = new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l));

		when(accountService.getAccountByBankName("SBI")).thenReturn(bankAccount);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bankAccount/bankname/{name}",
				"SBI");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		int status = response.getStatus();

		assertEquals(200, status);

		assertEquals("application/json", response.getContentType());
	}

	@Test
	void testGetAccountByType() throws Exception {

		List<BankAccount> listOfBankAccount = new ArrayList<>();
		listOfBankAccount.add(new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l)));
		listOfBankAccount.add(new BankAccount(2, "xyz456", "AXIS", "Saving", 22000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l)));
		listOfBankAccount.add(new BankAccount(3, "abc123", "HDFC", "Current", 6000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l)));
		listOfBankAccount.add(new BankAccount(4, "abc123", "SBI", "Saving", 45000l,
				new User(1, "Sumit Choudhari", "Sangli", "s@gmailc.com", 9307304360l)));

		when(accountService.getAccountByType("Saving")).thenReturn(listOfBankAccount);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bankAccount/accountType/{type}","Saving");
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
	}
	
	@Test
	void testGetBankAccountById() throws Exception {
		
		BankAccount bankAccount = new BankAccount(1, "abc123", "SBI", "Saving", 16000l,
				new User(1, "Parshuram Bhosekar", "Sangli", "p@gmailc.com", 9623771726l));

		when(accountService.getAccountById("abc123")).thenReturn(bankAccount);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bankAccount/{id}","abc123")
						.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
		
		assertEquals("application/json", response.getContentType());
	}

}
