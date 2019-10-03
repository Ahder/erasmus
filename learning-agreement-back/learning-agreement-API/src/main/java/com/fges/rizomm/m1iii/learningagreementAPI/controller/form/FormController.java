package com.fges.rizomm.m1iii.learningagreementAPI.controller.form;

import com.fges.rizomm.m1iii.learningagreementAPI.dto.form.FormDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.services.form.IFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/form")
public class FormController {

    @Autowired
    IFormService formService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FormDTO> findAllFormsForCurrentUser(Authentication authentication) {
        return this.formService.findAllForCurrentUsers(authentication);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FormDTO findOneForm(@PathVariable Long id) {
        return this.formService.findOne(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FormDTO addForm(@RequestBody FormDTO formDTO) {
        return this.formService.addForm(formDTO);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FormDTO editForm(@RequestBody FormDTO formDTO, @PathVariable Long id) {
        return this.formService.editForm(formDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteForm(@PathVariable Long id) {
        this.formService.deleteForm(id);
    }

    @PostMapping("/invite")
    public FormDTO passwordForgot(@RequestBody FormDTO formDTO) {
        return this.formService.invite(formDTO);
    }

    @GetMapping("/{token}")
    public FormDTO getByToken(@PathVariable String token) {
        return this.formService.getByToken(token);
    }

    @GetMapping("/signForm/{id}")
    public FormDTO signFormUniversity(@PathVariable Long id, @RequestBody FormDTO formDTO) {
        return this.formService.signFormUniversity(id,formDTO);
    }
}
