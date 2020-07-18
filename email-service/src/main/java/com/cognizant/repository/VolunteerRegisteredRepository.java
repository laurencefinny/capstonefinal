package com.cognizant.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.VolunteerRegistered;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface VolunteerRegisteredRepository extends ReactiveCrudRepository<VolunteerRegistered, Integer> {
	@Query("SELECT * FROM volunteer_reg WHERE employee_id = :empId")
	Mono<VolunteerRegistered> findByEmployeeId(String empId);

	@Query("SELECT * FROM volunteer_reg WHERE event_id = :eventId")
	Flux<VolunteerRegistered> findByEventId(String eventId);

}
