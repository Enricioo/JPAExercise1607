package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByEtaLessThan(int eta);

	List<User> findByName(String name);

	List<User> findByNameAndLastname(String name, String lastname);
}
