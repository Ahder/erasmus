package com.fges.rizomm.m1iii.learningagreementAPI.dto.form;

import com.fges.rizomm.m1iii.learningagreementAPI.dto.course.CourseDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormDTO {
    private Long id;
    private int schoolYearStart;
    private int schoolYearEnd;
    private short semester;
    private Date signatureDateStudent;
    private Date signatureDateRpiHost;
    private Date signatureDateRpiHome;
    private String signatureStudent;
    private String signatureRpiHost;
    private String signatureRpiHome;
    private String country;
    private UserDTO student;
    private List<CourseDTO> courses;
    private String hostEstablishment;
    private String studyDomain;
    private boolean isClosed;
    private Date createdAt;
    private Date lastModified;
    private String inviteToken;
    private String mailRpiHost;

}
