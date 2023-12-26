package com.workintech.controller;

import com.workintech.entity.Account;
import com.workintech.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    @GetMapping
    public List<Account> findAll(){
        return accountService.findAll();
    }
    @PostMapping
    public Account save(@RequestBody Account account){
        return accountService.save(account);
    }
}
