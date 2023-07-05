package com.parshuram.onetomanymapping.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parshuram.onetomanymapping.entity.BankAccount;
import com.parshuram.onetomanymapping.entity.User;
import com.parshuram.onetomanymapping.exception.ResourceNotFoundExcepton;
import com.parshuram.onetomanymapping.repository.BankAccountRepository;
import com.parshuram.onetomanymapping.repository.UserRepository;
import com.parshuram.onetomanymapping.service.BankAccountService;

@Service
public class BankAccountImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public BankAccount createBankAccount(BankAccount account, Integer userId) {
		
		String accountId = UUID.randomUUID().toString();
		
		User user = userRepository.findById(userId)
					.orElseThrow(()->new ResourceNotFoundExcepton("User", "userId", userId));
		
		account.setAccountId(accountId);
		account.setUser(user);
		
		BankAccount createAccount = accountRepository.save(account);

		return createAccount;
	}

	@Override
	public BankAccount updateBankAccount(BankAccount account, String accountId) {
		
		
		BankAccount findByaccountId = accountRepository.findByaccountId(accountId);
		
		if(findByaccountId==null) {
			 throw new ResourceNotFoundExcepton("account", "accountId", accountId);
		}
		else {
			BankAccount updateAccount = accountRepository.save(account);
			
			return updateAccount;
		}
		
	}

	@Override
	public List<BankAccount> getAllBankAccounts() {
		
		List<BankAccount> listOFBankAccounts = accountRepository.findAll();
		
		return listOFBankAccounts;
	}

	@Override
	public void deleteBankAccount(String accountId) {
		
		BankAccount findByaccountId = accountRepository.findByaccountId(accountId);
		
		if(findByaccountId==null) {
			throw new ResourceNotFoundExcepton("Account", "accountId", accountId);
			
		}
		else {
			accountRepository.delete(findByaccountId);
		}
								
	}

	@Override
	public BankAccount getAccountById(String accountId) {
		
		BankAccount findByaccountId = accountRepository.findByaccountId(accountId);
		
		if(findByaccountId==null) {
			throw new ResourceNotFoundExcepton("Account", "accountId", accountId);
			
		}
		
		return findByaccountId;
	}

	@Override
	public BankAccount getAccountByBankName(String bankName) {
		
		List<BankAccount> list = accountRepository.findAll();
		
		BankAccount bankAccount = list.stream().filter(t -> t.getBankName().equalsIgnoreCase(bankName))
						.findAny()
						.orElseThrow(()->new ResourceNotFoundExcepton("BankName", "bankName", bankName));
		
		
		return bankAccount;
	}

	@Override
	public List<BankAccount> getAccountByType(String type) {
		
		List<BankAccount> list = accountRepository.findAll();
		
		List<BankAccount> listOfAccounts = list.stream().filter(t -> t.getAccountType().equalsIgnoreCase(type))
						.collect(Collectors.toList());
		
		if(listOfAccounts.isEmpty()) {
			throw new ResourceNotFoundExcepton("AccountType", "type", type);
		}
		
		return listOfAccounts;
	}

}
