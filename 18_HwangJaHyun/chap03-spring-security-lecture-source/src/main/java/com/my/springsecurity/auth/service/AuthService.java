package com.my.springsecurity.auth.service;

import com.my.springsecurity.auth.dto.LoginRequest;
import com.my.springsecurity.auth.dto.TokenResponse;
import com.my.springsecurity.auth.entity.RefreshToken;
import com.my.springsecurity.auth.repository.AuthRepository;
import com.my.springsecurity.command.entity.User;
import com.my.springsecurity.command.repository.UserRepository;
import com.my.springsecurity.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthRepository authRepository;

  public TokenResponse login(LoginRequest request){

    // 1. ID(username)로 조회
    User user = userRepository.findByUsername(request.getUsername())
      .orElseThrow(
        () -> new BadCredentialsException(("아이디 또는 비밀번호가 일치하지 않습니다."))
      );

    // 2. 비밀번호 매칭 확인
    if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
      throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
    }

    // 3. 비밀번호 일치 -> 로그인 성공 -> 토큰 발급
    String accessToken = jwtTokenProvider.createToken(user.getUsername(), user.getRole().name());
    String refreshToken = jwtTokenProvider.createRefreshToken(user.getUsername(), user.getRole().name());

    // 4. RefreshToken 저장(보안 및 토큰 재발급 검증용)
    RefreshToken tokenEntity = RefreshToken.builder()
        .username(user.getUsername())
        .token(refreshToken)
        .expiryDate(new Date(System.currentTimeMillis()
          + jwtTokenProvider.getRefreshExpiration())
        ).build();

    authRepository.save(tokenEntity);

    return TokenResponse.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

}
