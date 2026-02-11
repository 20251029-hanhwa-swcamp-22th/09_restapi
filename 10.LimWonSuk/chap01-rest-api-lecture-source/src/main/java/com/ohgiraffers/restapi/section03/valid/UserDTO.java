package com.ohgiraffers.restapi.section03.valid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private int no;

    @NotNull(message = "By all means Id")
    @NotBlank(message = "Can not null")
    private String id;

    @Length(max = 10, message = " 비밍번호 길이는 10을 초과 할 수 없습니다.")
    private String pwd;


    @NotNull(message = "이름은 반드시 입력 되어야 합니다.")
    @Size(min=2, message="이름은 2글자 이상이어야 합니다.")
    private String name;



}