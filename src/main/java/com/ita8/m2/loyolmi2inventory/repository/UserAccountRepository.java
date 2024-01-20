package com.ita8.m2.loyolmi2inventory.repository;

import com.ita8.m2.loyolmi2inventory.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    UserAccount findByUsername(String username);
}
