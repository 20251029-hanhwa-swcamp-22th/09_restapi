package org.ho.springsecurity.auth.repository;

import org.ho.springsecurity.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<RefreshToken,String> {

  RefreshToken findByUsername(String username);
}
