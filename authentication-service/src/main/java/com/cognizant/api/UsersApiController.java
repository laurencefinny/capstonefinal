package com.cognizant.api;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.model.User;
import com.cognizant.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-19T15:35:34.885+05:30[Asia/Calcutta]")
@CrossOrigin("http://localhost:3000")
@Controller
public class UsersApiController implements UsersApi {

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private UserRepository userRepository;

	@org.springframework.beans.factory.annotation.Autowired
	public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public Optional<ObjectMapper> getObjectMapper() {
		return Optional.ofNullable(objectMapper);
	}

	@Override
	public Optional<HttpServletRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	public Mono<ResponseEntity<User>> getByUserId(Integer userId) {
		return userRepository.findById(userId).map(user -> ResponseEntity.ok(user))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();
	}

	public Flux<ResponseEntity<User>> allUsers() {
		return userRepository.findAll().map(user -> ResponseEntity.ok(user))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public Mono<ResponseEntity<User>> addUser(@Valid @RequestBody String userId) throws Exception {
		String addUser = userId.substring(2, userId.length() - 2);
		String userArr[] = addUser.split(" ");

		Mono<User> check = userRepository.findByEmpId(userArr[0]);

		if (check.block() != null) {
			throw new Exception("Invalid User Details");
		} else {
			User user = new User();
			user.setUs_emp_id(userArr[0]);
			user.setUs_fs_name(userArr[1]);
			user.setUs_ls_name(userArr[2]);
			user.setRole("Pmo");
			user.setUs_password(passwordEncoder().encode("pwd"));

			return userRepository.save(user).map(savedUser -> new ResponseEntity<>(savedUser, HttpStatus.CREATED));
		}

	}

	public Mono<ResponseEntity<Void>> removeUser(@PathVariable(value = "userId") String userId) {
		LOGGER.info("inside 	delete method");
		System.err.println(userId);
		return userRepository.findByEmpId(userId)
				.flatMap(user -> userRepository.delete(user).then(Mono.just(ResponseEntity.ok().<Void>build())))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}