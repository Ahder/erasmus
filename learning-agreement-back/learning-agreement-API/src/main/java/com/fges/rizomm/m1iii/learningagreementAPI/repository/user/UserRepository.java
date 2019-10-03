package com.fges.rizomm.m1iii.learningagreementAPI.repository.user;

import java.util.List;
import java.util.Optional;

import com.fges.rizomm.m1iii.learningagreementAPI.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	User findByResetToken(String resetToken);
	List<User> findAllByRpi(User user);
}