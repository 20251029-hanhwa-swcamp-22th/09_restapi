package com.jinosoft.restapi.section02.responseentity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entity")
public class ResponseEntityController {
	
	private List<UserDTO> users;
	
	public ResponseEntityController() {
		users = new ArrayList<>();
		
		users.add(
			new UserDTO(1, "user01", "pass01", "홍길동", new java.util.Date())
		);
		users.add(
			new UserDTO(2, "user02", "pass02", "유관순", new java.util.Date())
		);
		users.add(
			new UserDTO(3, "user03", "pass03", "이순신", new java.util.Date())
		);
		
	}

	@GetMapping("/users")
	public ResponseEntity<ResponseMessage> findAllUsers() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				new MediaType("application", "json", Charset.forName("UTF-8"))
		);

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("users", users);

		ResponseMessage responseMessage
				= new ResponseMessage(200, "조회 성공", responseMap);

		// return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);

		return ResponseEntity.ok().headers(headers).body(responseMessage);

	}

	/* 2. 특정 유저 조회 */
	@GetMapping("/users/{userNo}")
	public ResponseEntity<ResponseMessage> findUserByNo(
			@PathVariable("userNo") int userNo
	){
		/* 1) 응답 헤더 설정 */
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(
				new MediaType(
						"application",
						"json",
						StandardCharsets.UTF_8)
		);

		/* 2) 응답 바디 설정 */
		/*Map<String, Object> responseMap = new HashMap<>();

		UserDTO foundUser = users.stream()
				.filter(user -> user.getNo() == userNo)
				.findFirst().get();

		responseMap.put("user", foundUser);

		return ResponseEntity
				.ok()
				.headers(httpHeaders)
				.body(new ResponseMessage(
						200, "조회 성공", responseMap));*/

		/* 2+a. 예외 상황 추가 */
		return users.stream()
				.filter(user -> user.getNo() == userNo)
				.findFirst()
				.map(user -> {
					Map<String, Object> responseMap = new HashMap<>();
					responseMap.put("user", user);
					return ResponseEntity
							.ok()
							.headers(httpHeaders)
							.body(new ResponseMessage(
									200, "조회 성공", responseMap));
				}).orElseGet(()-> ResponseEntity.notFound().build());
	}

	/* 3. 유저 등록(POST) */
	/* @RequestBody :
	 * - 요청 바디에 담긴 데이터를 꺼내는 어노테이션
	 * - 옆에 작성된 DTO(필드명 == KEY 일치) 또는 Map에 데이터를 저장
	 *  */
	@PostMapping("/users")
	public ResponseEntity<Void> registUser(@RequestBody UserDTO newUser) {

		System.out.println(newUser);

		int lastUserNo = users.get(users.size() - 1).getNo();
		newUser.setNo(lastUserNo + 1);
		newUser.setEnrollDate(new java.util.Date());

		users.add(newUser);

		return ResponseEntity
				.created(
						URI.create("/entity/users/" + users.get(users.size() - 1).getNo())
				)
				.build();

	}

	/* 4. PUT 유저 수정 */
	@PutMapping("/users/{userNo}")
	public ResponseEntity<?> modifyUser(
			@PathVariable int userNo, @RequestBody UserDTO modifyInfo
	) {

		System.out.println(modifyInfo);

		UserDTO foundUser
				= users.stream().filter(user -> user.getNo() == userNo)
				.collect(Collectors.toList()).get(0);
		foundUser.setId(modifyInfo.getId());
		foundUser.setPwd(modifyInfo.getPwd());
		foundUser.setName(modifyInfo.getName());

		return ResponseEntity
				.created(URI.create("/entity/users/" + userNo))
				.build();
	}

	/* 5. 유저 삭제 */
	@DeleteMapping("/users/{userNo}")
	public ResponseEntity<?> removeUser(@PathVariable int userNo) {

		UserDTO foundUser
				= users.stream().filter(user -> user.getNo() == userNo)
				.collect(Collectors.toList()).get(0);

		users.remove(foundUser);

		return ResponseEntity
				.noContent()
				.build();
	}

}