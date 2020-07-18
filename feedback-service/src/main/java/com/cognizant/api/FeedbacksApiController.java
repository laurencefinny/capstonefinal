package com.cognizant.api;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.model.Feedback;
import com.cognizant.model.NotParticipatedFeedBack;
import com.cognizant.model.ParticipatedFeedBack;
import com.cognizant.model.UnRegisteredFeedback;
import com.cognizant.repository.NotparticipatedFeedbackRepository;
import com.cognizant.repository.ParticipatedFeedbackRepository;
import com.cognizant.repository.UnRegisteredFeedbackRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-04T10:53:05.768+05:30[Asia/Calcutta]")
@Controller
public class FeedbacksApiController implements FeedbacksApi {

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public FeedbacksApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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
	private ParticipatedFeedbackRepository participatedFeedbackRepository;

	@Autowired
	private NotparticipatedFeedbackRepository notparticipatedFeedbackRepository;

	@Autowired
	private UnRegisteredFeedbackRepository unRegisterdFeedbackRepository;
	
	public Flux<ResponseEntity<Feedback>> getFeedback(
			@ApiParam(value = "", required = true) @PathVariable("eventId") String eventId) {
		Feedback feedback = new Feedback();
		feedback.setParticipatedFeedBack(participatedFeedbackRepository.findByEventId(eventId).collectList().block());
		feedback.setNotParticipated(
				notparticipatedFeedbackRepository.findNotparticipatedByEventId(eventId).collectList().block());
		feedback.setUnregistered(
				notparticipatedFeedbackRepository.findUnRegisteredByEventId(eventId).collectList().block());
		Flux.just(feedback);

		return Flux.just(feedback).map(feedbacks -> ResponseEntity.ok().body(feedbacks))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();

	}
	@PostMapping(path = "/email/participated") 
	public Mono<ResponseEntity<Void>> addParticipatedFeedback( @RequestBody ParticipatedFeedBack body) {
		System.err.println(body);
		return participatedFeedbackRepository.save(body).map(fb -> ResponseEntity.noContent().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build()).log();
		//return null;


	}
	@PostMapping(path = "/email/notparticipated", consumes = "application/json", produces = "application/json") 
	public Mono<ResponseEntity<Void>> addNotParticipatedFeedback( @RequestBody NotParticipatedFeedBack body) {

		return notparticipatedFeedbackRepository.save(body).map(fb -> ResponseEntity.noContent().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build()).log();

	}
	@PostMapping(path = "/email/unreg", consumes = "application/json", produces = "application/json") 
	public Mono<ResponseEntity<Void>> addUnRegisterdFeedback( @RequestBody UnRegisteredFeedback body) {

		return unRegisterdFeedbackRepository.save(body).map(fb -> ResponseEntity.noContent().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build()).log();

	}
}
