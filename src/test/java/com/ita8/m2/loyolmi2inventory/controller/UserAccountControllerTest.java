package com.ita8.m2.loyolmi2inventory.controller;

import com.ita8.m2.loyolmi2inventory.entity.UserAccount;
import com.ita8.m2.loyolmi2inventory.service.UserAccountService;
import com.ita8.m2.loyolmi2inventory.service.dto.UserAccountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(UserAccountController.class)
public class UserAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAccountService userAccountService;

    @Test
    public void should_return_user_account_when_get_user_account_by_username_given_username() throws Exception {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(1);
        userAccount.setUsername("test");
        userAccount.setPassword("password");

        when(userAccountService.getUserAccountByUsername(eq("test"))).thenReturn(userAccount);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user-account/getByName/test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"username\":\"test\",\"password\":\"password\"}"));
    }

    @Test
    public void should_return_true_when_create_user_given_valid_account_details() throws Exception {
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setId(1);
        userAccountDto.setUsername("test");
        userAccountDto.setPassword("password");

        when(userAccountService.createUserAccount(any(UserAccountDto.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user-account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"username\":\"test\",\"password\":\"password\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    public void should_return_bad_request_when_create_user_given_null_values() throws Exception {
        when(userAccountService.createUserAccount(any(UserAccountDto.class))).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user-account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void should_return_accounts_when_get_all_users_when_accounts_exist() throws Exception {
        UserAccountDto userAccountDto1 = new UserAccountDto();
        userAccountDto1.setId(1);
        userAccountDto1.setUsername("test1");
        userAccountDto1.setPassword("password1");

        UserAccountDto userAccountDto2 = new UserAccountDto();
        userAccountDto2.setId(2);
        userAccountDto2.setUsername("test2");
        userAccountDto2.setPassword("password2");

        when(userAccountService.getAllAccounts()).thenReturn(Arrays.asList(userAccountDto1, userAccountDto2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user-account"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{\"id\":1,\"username\":\"test1\",\"password\":\"password1\"},{\"id\":2,\"username\":\"test2\",\"password\":\"password2\"}]"));
    }

    @Test
    public void should_return_empty_list_when_get_all_users_given_no_accounts() throws Exception {
        when(userAccountService.getAllAccounts()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user-account"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }
}