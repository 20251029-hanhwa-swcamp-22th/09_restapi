package org.ho.springsecurity.command.service;

import lombok.RequiredArgsConstructor;
import org.ho.springsecurity.command.dto.UserCreateRequest;
import org.ho.springsecurity.command.entity.User;
import org.ho.springsecurity.command.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCommandService {
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;

  /* USER 등록 */
  @Transactional
  public void registUser(UserCreateRequest userCreateRequest) {

    /* request(DTO) -> Entity */
    User user = modelMapper.map(userCreateRequest, User.class);

    /* 비밀번호를 암호화 하여 Entity에 세팅 */
    user.setEncodedPassword(
        passwordEncoder.encode(userCreateRequest.getPassword())
    );

    /* 저장 */
    userRepository.save(user);
  }


  /* ADMIN 등록 */
  @Transactional
  public void registAdmin(UserCreateRequest userCreateRequest) {

    /* request(DTO) -> Entity */
    User user = modelMapper.map(userCreateRequest, User.class);

    /* 비밀번호를 암호화 하여 Entity에 세팅 */
    user.setEncodedPassword(
        passwordEncoder.encode(userCreateRequest.getPassword())
    );

    /* 권한 변경 */
    user.modifyRole("ADMIN");

    /* 저장 */
    userRepository.save(user);
  }
}
