package com.cognizant.api;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.entity.Email;
import com.cognizant.model.Event;
import com.cognizant.model.VolunteerRegistered;
import com.cognizant.model.VolunteerUnRegistered;
import com.cognizant.repository.EventRepository;
import com.cognizant.repository.VolunteerRegisteredRepository;
import com.cognizant.repository.VolunteerUnRegisteredRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-02-25T09:06:38.313+05:30[Asia/Calcutta]")
@Controller
@CrossOrigin("http://localhost:3000")
public class EmailApiController implements EmailApi {

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public EmailApiController(ObjectMapper objectMapper, HttpServletRequest request) {
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
	private VolunteerRegisteredRepository volunteerRegisteredRepository;
	@Autowired
	private VolunteerUnRegisteredRepository volunteerUnRegisteredRepository;

	@Override
	public Mono<Boolean> sendEmail() {

		Email email = new Email();
		Email emailUnreg = new Email();
		Email emailNotAttempt = new Email();

		emailNotAttempt.setFrom("602367user@gmail.com");
		emailNotAttempt.setSubject("Tell us how much you like it");
		emailNotAttempt.setText(
				"Hi Participants, we kindly request you to provide valuable feedback by clicking the below link");

		emailUnreg.setFrom("602367user@gmail.com");
		emailUnreg.setSubject("Tell us how much you like it");
		emailUnreg.setText(
				"Hi Participants, we kindly request you to provide valuable feedback by clicking the below link");

		email.setFrom("602367user@gmail.com");
		email.setSubject("Tell us how much you like it");
		email.setText("Hi Participants, we kindly request you to provide valuable feedback by clicking the below link");

		Flux<VolunteerRegistered> reg = volunteerRegisteredRepository.findAll();
		Flux<VolunteerUnRegistered> un_reg = volunteerUnRegisteredRepository.findByStatusUnreg();
		Flux<VolunteerUnRegistered> NotAttempt = volunteerUnRegisteredRepository.findByStatusNotAttempt();

		String[] reg_email = reg.filter(r -> r.getEmployeeId() != null).map(VolunteerRegistered::getEmployeeId)
				.collectList().block().stream().map(emailAdd -> emailAdd + "@gmail.com").toArray(String[]::new);

		String[] un_reg_email = un_reg.filter(r -> r.getEmployeeId() != null).map(VolunteerUnRegistered::getEmployeeId)
				.collectList().block().stream().map(emailAdd -> emailAdd + "@gmail.com").toArray(String[]::new);

		String[] notAttempt = NotAttempt.filter(r -> r.getEmployeeId() != null)
				.map(VolunteerUnRegistered::getEmployeeId).collectList().block().stream()
				.map(emailAdd -> emailAdd + "@gmail.com").toArray(String[]::new);

		emailNotAttempt.setCc(un_reg.blockFirst().getEventName());
		emailUnreg.setCc(un_reg.blockFirst().getEventName());
		email.setCc(reg.blockFirst().getEventName());

		emailNotAttempt.setBcc("http://localhost:3000/not-attempted/" + un_reg.blockFirst().getEventId());
		emailUnreg.setBcc("http://localhost:3000/unreg/" + un_reg.blockFirst().getEventId());
		email.setBcc("http://localhost:3000/attempted/" + reg.blockFirst().getEventId());

		System.err.println(reg.map(r -> r.getEventId()).collectList());

		email.setTo(reg_email);
		emailUnreg.setTo(un_reg_email);
		emailNotAttempt.setTo(notAttempt);

		try {

			send(email);
			send(emailUnreg);
			send(emailNotAttempt);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Mono<Boolean> sendEventEmail(
			@ApiParam(value = "email", required = true) @PathVariable("eventId") String eventId) {

		Email email = new Email();
		Email emailUnreg = new Email();
		Email emailNotAttempt = new Email();

		emailNotAttempt.setFrom("602367user@gmail.com");
		emailNotAttempt.setSubject("Tell us how much you like it");
		emailNotAttempt.setText(
				"Hi Participants, we kindly request you to provide valuable feedback by clicking the below link");

		emailUnreg.setFrom("602367user@gmail.com");
		emailUnreg.setSubject("Tell us how much you like it");
		emailUnreg.setText(
				"Hi Participants, we kindly request you to provide valuable feedback by clicking the below link");

		email.setFrom("602367user@gmail.com");
		email.setSubject("Tell us how much you like it");
		email.setText("Hi Participants, we kindly request you to provide valuable feedback by clicking the below link");

		Flux<VolunteerRegistered> reg = volunteerRegisteredRepository.findByEventId(eventId);
		Flux<VolunteerUnRegistered> un_reg = volunteerUnRegisteredRepository.findByEventIdUnreg(eventId);
		Flux<VolunteerUnRegistered> NotAttempt = volunteerUnRegisteredRepository.findByEventIdNotAttempt(eventId);

		String[] reg_email = reg.filter(r -> r.getEmployeeId() != null).map(VolunteerRegistered::getEmployeeId)
				.collectList().block().stream().map(emailAdd -> emailAdd + "@gmail.com").toArray(String[]::new);

		String[] un_reg_email = un_reg.filter(r -> r.getEmployeeId() != null).map(VolunteerUnRegistered::getEmployeeId)
				.collectList().block().stream().map(emailAdd -> emailAdd + "@gmail.com").toArray(String[]::new);

		String[] notAttempt = NotAttempt.filter(r -> r.getEmployeeId() != null)
				.map(VolunteerUnRegistered::getEmployeeId).collectList().block().stream()
				.map(emailAdd -> emailAdd + "@gmail.com").toArray(String[]::new);
		Event event = new Event();
		event = eventRepository.findByEventIdName(eventId).block();
		String cc = event.getEventName();
		emailNotAttempt.setCc(cc);
		emailUnreg.setCc(cc);
		email.setCc(cc);

		emailNotAttempt.setBcc("http://localhost:3000/not-attempted/" + eventId);
		emailUnreg.setBcc("http://localhost:3000/unreg/" + eventId);
		email.setBcc("http://localhost:3000/attempted/" + eventId);

		email.setTo(reg_email);
		emailUnreg.setTo(un_reg_email);
		emailNotAttempt.setTo(notAttempt);
		// Email[] email1 = new Email[100];

		try {
			send(email);
			send(emailUnreg);
			send(emailNotAttempt);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.mailtrap.io");
		mailSender.setPort(587);
		mailSender.setUsername("8e98319f2d56a2");
		mailSender.setPassword("baa6e6a390fe4f");

//		 mailSender.setHost("smtp.gmail.com");
//		 mailSender.setPort(587);
//		 mailSender.setUsername("602367user@gmail.com");
//		 mailSender.setPassword("user@602367");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	// public boolean send(Email[] email){
	// return false;
	// }
	public Boolean send(Email email) throws MailException, InterruptedException, MessagingException {

		System.err.println("email" + email);
		System.out.println("mail bcc" + email.getBcc());

		MimeMessage mimeMessage = getJavaMailSender().createMimeMessage();

		MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "utf-8");
		message.setFrom(email.getFrom());
		message.setSubject(email.getSubject());
		message.setText("<div style=\"margin:25px;width:800px\">\n"
				+ "	<div style=\"border: blue solid 2px; text-align:center;\">\n"
				+ "		<div style=\"background-color: blue; color: white; padding: 1px ;\">\n"
				+ "			<h3>!!!  Help us to SERVE BETTER !!! </h3>\n" + "		</div>\n"
				+ "		<div style=\"width:800px; height:140px; padding: 3x\">\n" + "			<h3>" + email.getCc()
				+ "</h3>\n" + "			<p >" + email.getText() + "</p>\n" + "			<a href=" + email.getBcc()
				+ " style=\" background-color:#32CD42; color: white;padding: 8px 18px;text-align: center;text-decoration: none;display: inline-block;font-size: 14px;\"><< Click>></a>\n"
				+ "		</div>\n"
				+ "		<div style=\"background-color: blue; color: white; padding: 4px; font-size: small\">Â© Copyright 2020</div>\n"
				+ "	</div>\n" + "</div>\n" + "", true);
		if (email.getTo() != null) {
			message.setTo(email.getTo());
		} else {
			return null;
		}

		System.err.println(mimeMessage);
		getJavaMailSender().send(mimeMessage);

		return Boolean.TRUE;
	}

	@Autowired
	private EventRepository eventRepository;

	@GetMapping("/event/{eventId}")
	public Mono<ResponseEntity<Event>> getEventById(@PathVariable("eventId") String eventId) {
		return eventRepository.findByEventId(eventId).map(event -> ResponseEntity.ok().body(event))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();
	}

	@GetMapping("/email/report/{eventId}")
	public Mono<Boolean> sendReportEmail(
			@ApiParam(value = "email", required = true) @PathVariable("eventId") String eventId) {

		Email email = new Email();

		email.setFrom("602367user@gmail.com");
		email.setSubject("Tell us how much you like it");
		email.setText("Hi " +eventId.substring(0,eventId.indexOf("@"))+ ", we kindly request you to verify the below Attached Event Report");
		email.setCc(eventId);
		email.setBcc("http://localhost:3000/attempted/" + eventId);
		String arr[] = new String[100];
		email.setTo(arr);

		try {
			sendReport(email);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Boolean sendReport(Email email) throws MailException, InterruptedException, MessagingException {

		System.err.println("email" + email);
		System.out.println("mail bcc" + email.getBcc());

		MimeMessage mimeMessage = getJavaMailSender().createMimeMessage();

		MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "utf-8");
		message.setFrom(email.getFrom());
		message.setSubject(email.getSubject());
		FileSystemResource file = new FileSystemResource(new File("C:/Users/799460/Downloads/eventReport.csv"));
		
		message.setText("<div style=\"margin:25px;width:800px\">\n"
				+ "	<div style=\"border: blue solid 2px; text-align:center;\">\n"
				+ "		<div style=\"background-color: blue; color: white; padding: 1px ;\">\n"
				+ "			<h3>!!!  Help us to SERVE BETTER !!! </h3>\n" + "		</div>\n"
				+ "		<div style=\"width:800px; height:140px; padding: 3x\">\n" + "			<h3>" + "Event Report"
				+ "</h3>\n" + "			<p >" + email.getText() + "</p>\n" 
				+ "		</div>\n"
				+ "		<div style=\"background-color: blue; color: white; padding: 4px; font-size: small\">Â© Copyright 2020</div>\n"
				+ "	</div>\n" + "</div>\n" + "", true);
		message.addAttachment("eventReport.csv", file);
		if (email.getTo() != null) {
			message.setTo(email.getCc());
		} else {
			return null;
		}

		System.err.println(mimeMessage);
		getJavaMailSender().send(mimeMessage);

		return Boolean.TRUE;
	}

}
