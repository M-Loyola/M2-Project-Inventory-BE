package com.ita8.m2.loyolmi2inventory.service;

import com.ita8.m2.loyolmi2inventory.entity.UserAccount;
import com.ita8.m2.loyolmi2inventory.repository.UserAccountRepository;
import com.ita8.m2.loyolmi2inventory.service.dto.UserAccountDto;
import com.ita8.m2.loyolmi2inventory.service.mapper.UserAccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private UserAccountService userAccountService;

    private UserAccountDto userAccountDto;
    private UserAccount userAccount;

    @BeforeEach
    public void setUp() {
        userAccountDto = new UserAccountDto();
        userAccountDto.setId(1);
        userAccountDto.setUsername("test");
        userAccountDto.setPassword("password");

        userAccount = UserAccountMapper.toEntity(userAccountDto);
    }

    @Test
    public void should_return_true_when_create_user_given_valid_user_account_dto() {
        when(userAccountRepository.save(any(UserAccount.class))).thenReturn(userAccount);

        boolean result = userAccountService.createUserAccount(userAccountDto);

        assertTrue(result);
        verify(userAccountRepository, times(1)).save(userAccount);
    }

    @Test
    public void should_return_false_when_create_user_given_exception() {
        when(userAccountRepository.save(any(UserAccount.class))).thenThrow(new RuntimeException());

        boolean result = userAccountService.createUserAccount(userAccountDto);

        assertFalse(result);
        verify(userAccountRepository, times(1)).save(userAccount);
    }

    @Test
    public void should_return_list_of_users_when_get_all_accounts() {
        List<UserAccount> accounts = new ArrayList<>();
        accounts.add(userAccount);
        when(userAccountRepository.findAll()).thenReturn(accounts);

        List<UserAccountDto> result = userAccountService.getAllAccounts();

        assertEquals(1, result.size());
        assertEquals(userAccountDto.getId(), result.get(0).getId());
        assertEquals(userAccountDto.getUsername(), result.get(0).getUsername());
        assertEquals(userAccountDto.getPassword(), result.get(0).getPassword());
        verify(userAccountRepository, times(1)).findAll();
    }

    @Test
    public void should_return_empty_list_when_get_all_accounts_given_no_users() {
        when(userAccountRepository.findAll()).thenReturn(new ArrayList<>());

        List<UserAccountDto> result = userAccountService.getAllAccounts();

        assertTrue(result.isEmpty());
        verify(userAccountRepository, times(1)).findAll();
    }

    @Test
    public void should_return_account_when_get_user_account_by_username_given_existing_account() {
        when(userAccountRepository.findByUsername(anyString())).thenReturn(userAccount);

        UserAccount result = userAccountService.getUserAccountByUsername(userAccountDto.getUsername());

        assertNotNull(result);
        assertEquals(userAccount, result);
        verify(userAccountRepository, times(1)).findByUsername(userAccountDto.getUsername());
    }

    @Test
    public void should_return_null_when_get_user_account_by_username_given_not_existing_account() {
        when(userAccountRepository.findByUsername(anyString())).thenReturn(null);

        UserAccount result = userAccountService.getUserAccountByUsername("nonexistent");

        assertNull(result);
        verify(userAccountRepository, times(1)).findByUsername("nonexistent");
    }
}