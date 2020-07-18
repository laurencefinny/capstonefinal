package com.cognizant.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Event;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EventRepository extends ReactiveCrudRepository<Event, Integer> {

	@Query("select * from event where event_id = ?eventId")
	Mono<Event> findByEventId(String eventId);

	@Query("select * from event where poc_id = ?empId")
	Flux<Event> findByEmpId(String empId);
	
	@Query("select event_name from event where event_id = ?eventId")
	Mono<Event> findByEventIdName(String eventId);

}
