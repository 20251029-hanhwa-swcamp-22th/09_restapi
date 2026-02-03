package com.beyond.springsecurity.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
public class TokenResponse {

  private String accessToken;
  private String refreshToken;

}