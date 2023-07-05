package com.parshuram.onetomanymapping.service;

import java.util.List;

import com.parshuram.onetomanymapping.entity.BankAccount;

public interface BankAccountService {
	
	public BankAccount createBankAccount(BankAccount account,Integer userId);
	
	public BankAccount updateBankAccount(BankAccount account,String accountId);
	
	public List<BankAccount> getAllBankAccounts();
	
	public void deleteBankAccount(String accountId);
	
	public BankAccount getAccountById(String accountId);
	
	public BankAccount getAccountByBankName(String bankName);
	
	public List<BankAccount> getAccountByType(String type);

}
