package com.cognizant.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.cognizant.model.NotParticipatedFeedBack;
import com.cognizant.model.UnRegisteredFeedback;

import reactor.core.publisher.Flux;

public interface NotparticipatedFeedbackRepository extends ReactiveCrudRepository<NotParticipatedFeedBack, Integer> {

	@Query("select comment from un_reg where event_event_id = :eventId ")
	public Flux<UnRegisteredFeedback> findUnRegisteredByEventId(String eventId);

	@Query("select comment from non_participated where event_event_id = :eventId")
	public Flux<NotParticipatedFeedBack> findNotparticipatedByEventId(String eventId);

}
