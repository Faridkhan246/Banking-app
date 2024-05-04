package com.mapper;

import com.dto.AccountDto;
import com.entity.Account;

public class AccountMapper {

	public static Account maptoAccount(AccountDto accountDto) {
		Account account =new Account(
				accountDto.getId(),
				accountDto.getAccountHolderName(),	
				accountDto.getBalance()
				);
		
		return account;
}
	public static AccountDto mapToAccountDto(Account account) {
		AccountDto accountDto=new AccountDto(account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				);
		return accountDto;
	}
}
