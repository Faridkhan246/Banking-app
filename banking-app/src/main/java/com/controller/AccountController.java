package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.AccountDto;
import com.service.AccountService;

@RestController	
@RequestMapping("/api/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	public AccountController(AccountService accountService)
	{
	this.accountService=accountService;
	
	
	}
//	Add Account Rest Api
	@PostMapping
	public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto accountDto)
	{
		return new ResponseEntity<>(accountService.creatAccount(accountDto),HttpStatus.CREATED);
	}
	//Get Account rest Api
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(Long id){
		AccountDto accountDto=accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	//Deposit Rest Api
	@PutMapping("/{id}/deposit")
	
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
			@RequestBody Map<String,Double>request){
		
		Double amount=request.get("amount");
		AccountDto accountDto= accountService.deposit(id,request.get("amount"));
		return ResponseEntity.ok(accountDto);
		}
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
			@RequestBody Map<String,Double> request){
		
		double amount =request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//Get All Accounts Rest Api
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts=accountService.getAllAccount();
		return ResponseEntity.ok(accounts);
	} 
	//Delete Account Rest Api
	@DeleteMapping
	public ResponseEntity<String> deleteAccount(Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is deleted Succesfully! ");
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	

