package com.cognizant.api;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.entity.FeedbackQuestions;
import com.cognizant.model.Answers;
import com.cognizant.model.Questions;
import com.cognizant.repository.AnswersRepository;
import com.cognizant.repository.QuestionsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-07-04T10:53:05.768+05:30[Asia/Calcutta]")
@Controller
@CrossOrigin("http://localhost:3000")
public class EditQuestionApiController implements EditQuestionApi {

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public EditQuestionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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
	private DatabaseClient databaseClient;

	@Autowired
	private QuestionsRepository queRepository;

	@Autowired
	private AnswersRepository ansRepository;

	public Flux<ResponseEntity<FeedbackQuestions>> getFeedbackQuestions() {
		return databaseClient
				.execute(
						"SELECT a.id,question,fb_type, COUNT(b.answers) AS tot_ans FROM questions a INNER JOIN answers b WHERE a.id = b.questions_id  GROUP BY a.id")
				.as(FeedbackQuestions.class).fetch().all().map(fb_list -> ResponseEntity.ok(fb_list))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();

	}

	@Override
	public Mono<ResponseEntity<Questions>> getQuestion(
			@ApiParam(value = "", required = true) @PathVariable("questionId") Integer questionId) {

		return queRepository.findById(questionId).map(que -> ResponseEntity.ok().body(que))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();

	}

	@Override
	public Flux<ResponseEntity<Answers>> getAnswers(
			@ApiParam(value = "", required = true) @PathVariable("ansId") Integer ansId) {
		return databaseClient.execute("SELECT * FROM fms.answers where questions_id= " + ansId).as(Answers.class)
				.fetch().all().map(ans -> ResponseEntity.ok(ans))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();
	}

	@Override
	public Flux<ResponseEntity<Questions>> getAllQuestions() {

		return queRepository.findAll().map(que -> ResponseEntity.ok().body(que))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();
	}

	@Override
	public Mono<ResponseEntity<Void>> updateQuestion(
			@ApiParam(value = "", required = true) @Valid @RequestBody Questions body,
			@PathVariable("questionId") Integer questionId) {
		System.err.println(body);

		return queRepository.save(body).map(user -> ResponseEntity.noContent().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build()).log();

	}

	@Override
	public Flux<ResponseEntity<Void>> updateAnswers(
			@ApiParam(value = "", required = true) @Valid @RequestBody List<Answers> body) {

		Flux<Answers> daoFlux = Flux.just(body).flatMap(Flux::fromIterable);
		System.err.println(body);
		return daoFlux.flatMap(ans -> ansRepository.save(ans).retry(body.size()))
				.map(user -> ResponseEntity.noContent().<Void>build()).defaultIfEmpty(ResponseEntity.notFound().build())
				.log();

	}

	@Override
	public Flux<ResponseEntity<Void>> addAnswers(
			@ApiParam(value = "", required = true) @Valid @RequestBody List<Answers> body) {

		Flux<Answers> daoFlux = Flux.just(body).flatMap(Flux::fromIterable);
		return daoFlux.flatMap(ans -> ansRepository.save(ans)).map(user -> ResponseEntity.noContent().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build()).log();

	}

	@Override
	public Mono<ResponseEntity<Void>> deleteQuestion(
			@ApiParam(value = "", required = true) @Valid @RequestBody Questions body,
			@PathVariable("questionId") Integer questionId) {

		return queRepository.deleteById(questionId).map(user -> ResponseEntity.noContent().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build()).log();

	}

	@Override
	public Flux<ResponseEntity<Void>> deleteAllAnswers(
			@ApiParam(value = "", required = true) @Valid @RequestBody List<Answers> body) {

		Flux<Answers> daoFlux = Flux.just(body).flatMap(Flux::fromIterable);

		return daoFlux.flatMap(ans -> ansRepository.delete(ans).retry(body.size()))
				.map(user -> ResponseEntity.noContent().<Void>build()).defaultIfEmpty(ResponseEntity.notFound().build())
				.log();

	}
	
	
	
	@GetMapping("/email/que/{type}")
	public Mono<ResponseEntity<Questions>> getQuestionByType(@PathVariable("type") String type) {

		return queRepository.findByType(type).map(que -> ResponseEntity.ok().body(que))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();

	}

	@GetMapping("/email/ans/{ansId}")
	public Flux<ResponseEntity<Answers>> getAnswersByType( @PathVariable("ansId") Integer ansId) {
		return databaseClient.execute("SELECT * FROM fms.answers where questions_id= " + ansId).as(Answers.class)
				.fetch().all().map(ans -> ResponseEntity.ok(ans))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();
	}
	
	

}
