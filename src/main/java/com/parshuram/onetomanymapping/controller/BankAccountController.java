package com.parshuram.onetomanymapping.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parshuram.onetomanymapping.entity.ApiResponse;
import com.parshuram.onetomanymapping.entity.BankAccount;
import com.parshuram.onetomanymapping.service.BankAccountService;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {
	
	@Autowired
	private BankAccountService accountService;
	
	
	@PostMapping("/user/{id}/account/")
	public ResponseEntity<BankAccount> createBankAccount(@Valid @RequestBody BankAccount account,@PathVariable(name = "id") Integer userId){
		
		BankAccount createBankAccount = accountService.createBankAccount(account, userId);
		
		return new ResponseEntity<BankAccount>(createBankAccount, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BankAccount> updateBankAccount(@Valid @RequestBody BankAccount account,@PathVariable(name = "id") String accountId){
		
		BankAccount updateBankAccount = accountService.updateBankAccount(account, accountId);
		
		return new ResponseEntity<BankAccount>(updateBankAccount, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<BankAccount>> listofAllAccounts(){
		
		List<BankAccount> allBankAccounts = accountService.getAllBankAccounts();
		
		return new ResponseEntity<List<BankAccount>>(allBankAccounts, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteAccount(@PathVariable(name="id") String accountId){
		
		accountService.deleteBankAccount(accountId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Account deleted Successfully....", true), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BankAccount> getAccountById(@PathVariable(name = "id") String accountId){
		
		BankAccount accountById = accountService.getAccountById(accountId);
		
		return new ResponseEntity<BankAccount>(accountById, HttpStatus.OK);
		
	}
	
	@GetMapping("/bankname/{name}")
	public ResponseEntity<BankAccount> getAccountByName(@PathVariable(name = "name") String bankName){
		
		BankAccount accountByBankName = accountService.getAccountByBankName(bankName);
		
		return new ResponseEntity<BankAccount>(accountByBankName, HttpStatus.OK);
		
	}
	
	@GetMapping("/accountType/{type}")
	public ResponseEntity<List<BankAccount>> getAccountByType(@PathVariable String type){
		
		List<BankAccount> accountByType = accountService.getAccountByType(type);
		
		return new ResponseEntity<List<BankAccount>>(accountByType, HttpStatus.OK);
	}

}
