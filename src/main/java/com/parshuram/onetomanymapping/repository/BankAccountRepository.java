package com.parshuram.onetomanymapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parshuram.onetomanymapping.entity.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

	BankAccount findByaccountId(String accountId);
	
}
