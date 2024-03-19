package com.user.ums.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.ums.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
}
