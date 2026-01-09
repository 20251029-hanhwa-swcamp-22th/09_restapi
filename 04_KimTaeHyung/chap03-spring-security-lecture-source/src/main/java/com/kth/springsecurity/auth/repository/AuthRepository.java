package com.kth.springsecurity.auth.repository;

import com.kth.springsecurity.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

// id 어노테이션이 붙은 컬럼의 타입을 붙이면 됨
public interface AuthRepository extends JpaRepository<RefreshToken, String> {
}
