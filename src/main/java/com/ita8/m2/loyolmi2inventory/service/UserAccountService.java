package com.ita8.m2.loyolmi2inventory.service;

import com.ita8.m2.loyolmi2inventory.entity.UserAccount;
import com.ita8.m2.loyolmi2inventory.repository.UserAccountRepository;
import com.ita8.m2.loyolmi2inventory.service.dto.UserAccountDto;
import com.ita8.m2.loyolmi2inventory.service.mapper.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }
    public boolean createUserAccount(UserAccountDto userAccountDto) {
        UserAccount account = UserAccountMapper.toEntity(userAccountDto);
        try {
            userAccountRepository.save(account);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public List<UserAccountDto> getAllAccounts() {
        List<UserAccount> accounts = userAccountRepository.findAll();
        return accounts.stream()
                .map(UserAccountMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserAccount getUserAccountByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }
}
