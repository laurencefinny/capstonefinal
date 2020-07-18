/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.16).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.cognizant.api;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognizant.model.Questions;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Mono;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-04T10:53:05.768+05:30[Asia/Calcutta]")
@Api(value = "Add-question", description = "the Add-question API")
@CrossOrigin("http://localhost:3000")
public interface AddQuestionApi {

	Logger log = LoggerFactory.getLogger(AddQuestionApi.class);

	default Optional<ObjectMapper> getObjectMapper() {
		return Optional.empty();
	}

	default Optional<HttpServletRequest> getRequest() {
		return Optional.empty();
	}

	default Optional<String> getAcceptHeader() {
		return getRequest().map(r -> r.getHeader("Accept"));
	}

	@ApiOperation(value = "Add Question and Answers", nickname = "addQA", notes = "Add Question and Answersto theEvent", response = Questions.class, tags = {
			"Question and Answers", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Questions.class),
			@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/add-question", consumes = { "application/json",
			"application/xml" }, method = RequestMethod.POST)
	default Mono<ResponseEntity<Void>> addQA(
			@ApiParam(value = "", required = true) @Valid @RequestBody Questions body) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return Mono.just(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED));
				} catch (Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return Mono.just(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
				}
			}
		} else {
			log.warn(
					"ObjectMapper or HttpServletRequest not configured in default RoleApi interface so no example is generated");
		}
		return Mono.just(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED));
	}

}
