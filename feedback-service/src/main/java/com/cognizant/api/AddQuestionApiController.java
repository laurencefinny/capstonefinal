package com.cognizant.api;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.model.Questions;
import com.cognizant.repository.QuestionsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Mono;

@CrossOrigin("http://localhost:3000")

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-04T10:53:05.768+05:30[Asia/Calcutta]")
@Controller
public class AddQuestionApiController implements AddQuestionApi {

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public AddQuestionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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

	@Autowired
	private QuestionsRepository queRepository;

	@Override
	public Mono<ResponseEntity<Void>> addQA(@ApiParam(value = "", required = true) @Valid @RequestBody Questions body) {
		System.err.println(body);
		return queRepository.save(body).map(user -> ResponseEntity.noContent().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build()).log();

	}
}
