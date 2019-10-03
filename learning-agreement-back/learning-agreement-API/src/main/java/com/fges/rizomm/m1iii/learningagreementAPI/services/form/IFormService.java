package com.fges.rizomm.m1iii.learningagreementAPI.services.form;

import com.fges.rizomm.m1iii.learningagreementAPI.dto.form.FormDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IFormService {

    FormDTO addForm(FormDTO courseDTO);
    List<FormDTO> findAll();
    FormDTO editForm(FormDTO courseDTO, Long id);
    void deleteForm(Long id);
    FormDTO findOne(Long id);
    List<FormDTO> findAllForCurrentUsers(Authentication authentication);
    FormDTO invite(FormDTO formDTO);
    FormDTO getByToken(String token);
    FormDTO signFormUniversity(Long id, FormDTO formDTO);
}
