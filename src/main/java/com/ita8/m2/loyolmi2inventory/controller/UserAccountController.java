package com.ita8.m2.loyolmi2inventory.controller;

import com.ita8.m2.loyolmi2inventory.entity.UserAccount;
import com.ita8.m2.loyolmi2inventory.service.UserAccountService;
import com.ita8.m2.loyolmi2inventory.service.dto.UserAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-account")
public class UserAccountController {
    private final UserAccountService userAccountService;
    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }
    @PostMapping("/create")
    public boolean createUser(@RequestBody UserAccountDto userAccountDto) throws IllegalArgumentException {
        return userAccountService.createUserAccount(userAccountDto);
    }
    @GetMapping
    public List<UserAccountDto> getAllUsers(){
        return userAccountService.getAllAccounts();
    }

    @GetMapping("/getByName/{username}")
    public UserAccount getUserAccountByUsername(@PathVariable String username) {
        return userAccountService.getUserAccountByUsername(username);
    }

}
