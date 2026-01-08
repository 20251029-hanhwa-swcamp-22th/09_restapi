package com.my.springsecurity.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListResponse {
  private List<UserDTO> users;
}
