package com.ita8.m2.loyolmi2inventory.service.mapper;

import com.ita8.m2.loyolmi2inventory.entity.UserAccount;
import com.ita8.m2.loyolmi2inventory.service.dto.UserAccountDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserAccountMapper {
    public static UserAccountDto toDto(UserAccount account) {
        UserAccountDto userAccountDto = new UserAccountDto();
        BeanUtils.copyProperties(account, userAccountDto);
        return userAccountDto;
    }
    public static UserAccount toEntity(UserAccountDto accountDto) {
        UserAccount accountEntity = new UserAccount();
        BeanUtils.copyProperties(accountDto, accountEntity);
        return accountEntity;
    }
}
