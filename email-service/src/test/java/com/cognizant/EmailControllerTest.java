package com.cognizant;
//package com.fms;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//@ActiveProfiles("test")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class EmailControllerTest {
//
//	@Autowired
//	private WebTestClient webTestClient;
//
//	@Test
//	public void testSendEmail() {
//		webTestClient.get().uri("/sendEmail").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();
//	}
//
//	@Test
//	public void testSendEventEmail() {
//		webTestClient.get().uri("/sendEventEmail/EVNT00047261").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();
//	}
//}
