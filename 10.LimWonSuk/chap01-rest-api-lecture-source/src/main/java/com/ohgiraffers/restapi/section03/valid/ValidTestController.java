package com.ohgiraffers.restapi.section03.valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

// Valid = 유효한
// 예외는 두가지가 있다  java가만든거 , 우리가만든거
@RestController // @Controller + @ResposeBody
@RequestMapping("/valid")
public class ValidTestController {
    @GetMapping("/users/{userNo}")
    public ResponseEntity<?> findUserByNo() throws UserNotFoundException { // Handler Mapping

        boolean check = true; // 강제로 예외를 만드는 코드
        if (check) {
            throw new UserNotFoundException("회원 정보를 찾을 수 없습니다.");
        }

        return ResponseEntity.ok().build(); //Handler Mapping을 통해 client 반환
    }

    /* 유저 등록 핸들러 메서드
     * - RequestBody를 톻ㅇ해 전달받은 데이터  UsersDTO 필드값
     *   유효성 검사 수행 (@Validated) */
    @PostMapping("/users")
    public ResponseEntity<?> registUser(@Validated @RequestBody UserDTO user) {

        System.out.println(user);

        return ResponseEntity
                .created(URI.create("/valid/users/" + "userNo"))
                .build();

    }

    /* MethodArgumentNotValidException : @Valide*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(
            MethodArgumentNotValidException e
    ) {

        String code = "";
        String description = "";
        String detail = "";

        /* 에러가 있다면 */
        if (e.getBindingResult().hasErrors()) {
            detail
                    = e.getBindingResult().getFieldError().getDefaultMessage();

            String bindResultCode
                    = e.getBindingResult().getFieldError().getCode();
            System.out.println(bindResultCode);

            switch (bindResultCode) {
                case "NotNull":
                    code = "ERROR_CODE_00001";
                    description = "필수 값이 누락되었습니다.";
                    break;
                case "NotBlank":
                    code = "ERROR_CODE_00002";
                    description = "필수 값이 공백으로 처리되었습니다.";
                    break;
                case "Size":
                    code = "ERROR_CODE_00003";
                    description = "알맞은 크기의 값이 입력되지 않았습니다.";
                    break;
                case "Length":
                    code = "ERROR_CODE_00004";
                    description = "알맞은 길이의 값이 입력되지 않았습니다.";
                    break;
            }
        }

        ErrorResponse errorResponse
                = new ErrorResponse(code, description, detail);

      //  return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

      //  return ResponseEntity.badRequest().body(errorResponse);
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

}
