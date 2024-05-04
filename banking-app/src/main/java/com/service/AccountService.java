package com.service;

import java.util.List;

import com.dto.AccountDto;

public interface AccountService {
	
	 AccountDto creatAccount(AccountDto accountDto) ;
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id,double amount);
	
	AccountDto withdraw(Long id,double amount );
	
	List<AccountDto> getAllAccount();
	
	void deleteAccount(Long id);
}

