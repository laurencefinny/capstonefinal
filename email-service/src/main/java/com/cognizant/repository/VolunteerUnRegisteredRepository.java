package com.cognizant.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.VolunteerUnRegistered;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface VolunteerUnRegisteredRepository extends ReactiveCrudRepository<VolunteerUnRegistered, Integer> {
	@Query("SELECT * FROM volunteer_unreg WHERE employee_id = :empId")
	Mono<VolunteerUnRegistered> findByEmployeeId(String empId);

	@Query("SELECT * FROM volunteer_unreg WHERE event_id = :eventId")
	Flux<VolunteerUnRegistered> findByEventId(String eventId);
	
	@Query("SELECT * FROM volunteer_unreg WHERE status = 'NotAttempt'")
	Flux<VolunteerUnRegistered> findByStatusNotAttempt();
	
	@Query("SELECT * FROM volunteer_unreg WHERE status = 'UnReg'")
	Flux<VolunteerUnRegistered> findByStatusUnreg();
	
	@Query("SELECT * FROM volunteer_unreg WHERE event_id = :eventId AND status = 'NotAttempt'")
	Flux<VolunteerUnRegistered> findByEventIdNotAttempt(String eventId);
	
	@Query("SELECT * FROM volunteer_unreg WHERE event_id = :eventId AND status = 'UnReg'")
	Flux<VolunteerUnRegistered> findByEventIdUnreg(String eventId);

}
