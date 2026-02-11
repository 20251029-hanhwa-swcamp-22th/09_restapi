package com.ohgiraffers.restapi.section01.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Message {
    private int httpStatusCode;
            private String Message;
}
