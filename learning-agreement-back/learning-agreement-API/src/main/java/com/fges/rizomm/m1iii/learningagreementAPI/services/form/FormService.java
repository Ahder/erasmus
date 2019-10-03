package com.fges.rizomm.m1iii.learningagreementAPI.services.form;

import com.fges.rizomm.m1iii.learningagreementAPI.components.SendMail;
import com.fges.rizomm.m1iii.learningagreementAPI.dto.course.CourseDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.dto.form.FormDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.dto.user.UserDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.course.Course;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.form.Form;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.user.User;
import com.fges.rizomm.m1iii.learningagreementAPI.exception.formNotFound.FormNotFoundException;
import com.fges.rizomm.m1iii.learningagreementAPI.repository.form.FormRepository;
import com.fges.rizomm.m1iii.learningagreementAPI.services.SuperService;
import com.fges.rizomm.m1iii.learningagreementAPI.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FormService extends SuperService<Form, FormDTO> implements IFormService {

    private final FormRepository formRepository;
    private final UserService userService;
    private final SendMail sendMail;

    @Autowired
    public FormService(FormRepository formRepository, UserService userService, SendMail sendMail) {
        this.formRepository = formRepository;
        this.userService = userService;
        this.sendMail = sendMail;
    }

    public FormDTO entityToDto(Form entity) {
        return super.getMapper().map(entity, FormDTO.class);
    }

    public Form dtoToEntity(FormDTO dto) {
        return super.getMapper().map(dto, Form.class);
    }

    @Override
    public FormDTO addForm(FormDTO formDTO) {
        Form form = this.dtoToEntity(formDTO);
        for (Course course : form.getCourses()) {
            course.setForm(form);
        }
        return this.entityToDto(this.formRepository.save(form));
    }

    @Override
    public List<FormDTO> findAll() {
        return this.entityListToDtoList(this.formRepository.findAll());
    }

    @Override
    public List<FormDTO> findAllForCurrentUsers(Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();
        UserDTO userDTO = this.userService.entityToDto(userDetails);
        switch (userDTO.getRole().toString()) {
            case "ADMIN":
                return this.findAll();
            case "RPI":
                return this.entityListToDtoList(this.formRepository.findAllByStudent_Rpi(userDetails));
            case "STUDENT":
            default:
                return this.entityListToDtoList(this.formRepository.findAllByStudent(userDetails));
        }

    }

    @Override
    public FormDTO findOne(Long id) {
        return this.entityToDto(this.formRepository.findById(id).orElseThrow(() -> new FormNotFoundException(id)));
    }

    @Override
    public FormDTO editForm(FormDTO formDTO, Long id) {
        return this.formRepository.findById(id).map(form -> {
            formDTO.setId(id);
            return this.entityToDto(this.formRepository.save(this.dtoToEntity(formDTO)));
        }).orElseThrow(() -> new FormNotFoundException(id));

    }

    @Override
    public void deleteForm(Long id) {
        Form formToDelete = this.formRepository.findById(id).orElseThrow(() -> new FormNotFoundException(id));
        this.formRepository.delete(formToDelete);
    }

    @Override
    public FormDTO invite(FormDTO formDTO) {
        Optional<Form> mayForm = this.formRepository.findById(formDTO.getId());
        if (mayForm.isPresent()) {
            Form form = mayForm.get();
            form.setInviteToken(Long.toString(System.currentTimeMillis()));
            FormDTO formDTO1 = this.entityToDto(formRepository.save(form));
            this.sendMail.sendMailForInviteToken(formDTO1);
            return formDTO1;
        } else {
            throw new FormNotFoundException(formDTO.getId());
        }
    }

    @Override
    public FormDTO getByToken(String token) {
        Form form = formRepository.findByInviteToken(token);
        FormDTO formDTO = null;
        if (form != null) {
            formDTO = this.entityToDto(form);
        }
        return formDTO;
    }

    @Override
    public FormDTO signFormUniversity(Long id, FormDTO formDTO) {
        return this.formRepository.findById(id).map(form -> {
            form.setSignatureDateRpiHost(new Date());
            form.setSignatureRpiHost(formDTO.getSignatureRpiHost());
            return this.entityToDto(this.formRepository.save(form));
        }).orElseThrow(() -> new FormNotFoundException(id));
    }

}
