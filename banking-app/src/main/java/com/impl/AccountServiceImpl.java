package com.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.AccountDto;
import com.entity.Account;
import com.mapper.AccountMapper;
import com.repository.AccountRepository;
import com.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	
	
	
	@Override
	public void deleteAccount(Long id) {
		 {
	Account account	=accountRepository
			.findById(id)
			.orElseThrow(()->new RuntimeException("Account Does not exists"));
			
	accountRepository.deleteById(id);
			}
			
	}

	@Override
	public List<AccountDto> getAllAccount() {
		List<Account> accounts =accountRepository.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account))
        .collect(Collectors.toList());
		
	}
    @Autowired
	private AccountRepository accountRepository; 
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository=accountRepository;
		
	}

	@Override
	public AccountDto creatAccount(AccountDto accountDto) {
		Account account =AccountMapper.maptoAccount(accountDto);
		Account savedAcccount =accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAcccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
	Account account	=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account Does not exists"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account	=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account Does not exists"));
		double total =account.getBalance()+ amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) 
	 {
			Account account	=accountRepository
	.findById(id)
	.orElseThrow(()->new RuntimeException("Account Does not exists"));
	
			if(account.getBalance()<amount) {
				throw new RuntimeException("Insufficent amount");
			}
			double total=account.getBalance()-amount;
			account.setBalance(total);
			Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

}
