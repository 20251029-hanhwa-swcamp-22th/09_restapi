package com.ohgiraffers.restapi.section04.hateoas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private int no;
    private String id;
    private String name;
    private String pwd;
    private Date enrollDate;


}