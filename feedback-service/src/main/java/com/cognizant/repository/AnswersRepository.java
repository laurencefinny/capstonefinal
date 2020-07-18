package com.cognizant.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.cognizant.model.Answers;

public interface AnswersRepository extends ReactiveCrudRepository<Answers, Integer> {

}