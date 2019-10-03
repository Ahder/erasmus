package com.fges.rizomm.m1iii.learningagreementAPI.repository.form;

import com.fges.rizomm.m1iii.learningagreementAPI.entity.form.Form;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findAllByStudent(User user);
    List<Form> findAllByStudent_Rpi(User user);
    Form findByInviteToken(String token);
}
