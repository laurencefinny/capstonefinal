package com.cognizant.repository;

import java.util.Map;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
	@Query("SELECT * FROM user WHERE us_emp_id = :empId")
	Mono<User> findByEmpId(String empId);

	@Query("DELETE FROM user WHERE us_emp_id = :empId")
	Mono<User> delete(String empId);

	@Query("SELECT * FROM user WHERE role = Pmo")
	Flux<User> findByRole();

	@Query("UPDATE user SET us_password = :password WHERE us_emp_id = :empId")
	Mono<Void> update(@Param(value = "password") String password, @Param(value = "empId") String empId);

	@Query("UPDATE user SET role = :password ,us_fs_name = 'New_Admin' WHERE us_emp_id = :empId")
	Mono<Void> promote(@Param(value = "password") String password, @Param(value = "empId") String empId);
	
}
