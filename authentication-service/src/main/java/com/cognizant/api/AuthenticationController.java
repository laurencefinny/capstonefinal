package com.cognizant.api;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.User;
import com.cognizant.repository.UserRepository;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationController {

	static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private UserRepository userRepository;

	@ApiOperation(value = "", nickname = "loginPost", notes = "get user by id", response = User.class, tags = {
			"login user", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "user logged in", response = User.class),
			@ApiResponse(code = 404, message = "No DataFound") })
	@RequestMapping(value = "/authenticate", produces = { "application/json" }, method = RequestMethod.GET)
	@CrossOrigin("http://localhost:3000")
	public Mono<Map<String, String>> loginPost(
			@ApiParam(value = "") @RequestHeader(value = "Authorization", required = true) String authHeader) {

		LOGGER.info("start inside authenticate method");
		LOGGER.debug(authHeader);
		System.err.println(authHeader);

		Map<String, String> jwt = new HashMap<String, String>();

		String username = getUser(authHeader);
		User user = userRepository.findByEmpId(username).block();
		Integer userId = user.getUs_id();
		String token = generateJwt(username);

		jwt.put("token", token);
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
		jwt.put("role", role);
		jwt.put("username", user.getUs_fs_name());
		jwt.put("userEmpId", user.getUs_emp_id());

		LOGGER.info("end of authenticate method");
		return Mono.just(jwt);
	}

	private String getUser(String authHeader) {
		String encodedCredentials = authHeader.split(" ")[1];

		byte[] decodedCredentials = Base64.getDecoder().decode(encodedCredentials);
		String user = new String(decodedCredentials).split(":")[0];
		LOGGER.debug(user);
		return user;
	}

	private String generateJwt(String user) {

		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);

		builder.setIssuedAt(new Date());

		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");

		String token = builder.compact();

		return token;
	}

	@GetMapping("forget/{lsname}/{emailid}")
	@CrossOrigin("http://localhost:3000")
	public boolean forget(@PathVariable(value = "lsname") String lsname,
			@PathVariable(value = "emailid") String emailid) {
		User user = userRepository.findByEmpId(emailid).block();
		if (lsname.equals(user.getUs_ls_name())) {
			return true;
		} else {
			return false;
		}
	}

	@GetMapping("reset/{pwd}/{empId}")
	@CrossOrigin("http://localhost:3000")
	public String Reset(@PathVariable(value = "pwd") String pwd, @PathVariable(value = "empId") String empId) {
		// User user = userRepository.findByEmpId(emailid).block();
		String password = passwordEncoder().encode(pwd);
		System.out.println(password);
		userRepository.update(password, empId).block();
		User user = userRepository.findByEmpId(empId).block();
		System.out.println(user);
		return "Success";
	}

	@GetMapping("promote/{emailid}")
	@CrossOrigin("http://localhost:3000")
	public boolean promote(@PathVariable(value = "emailid") String emailid) {
		User user = userRepository.findByEmpId(emailid).block();
		userRepository.promote("Admin", emailid).block();
		return true;
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}