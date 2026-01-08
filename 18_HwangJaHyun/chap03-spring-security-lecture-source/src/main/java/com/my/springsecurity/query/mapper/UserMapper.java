package com.my.springsecurity.query.mapper;

import com.my.springsecurity.query.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  UserDTO findUserByUsername(String username);


}
