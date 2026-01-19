package org.ho.springsecurity.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.ho.springsecurity.query.dto.UserDTO;

import java.util.List;

@Mapper
public interface UserMapper {

  UserDTO findUserByUsername(String username);

  List<UserDTO> findAllUsers();
}
