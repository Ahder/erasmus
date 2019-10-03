package com.fges.rizomm.m1iii.learningagreementAPI.entity.form;

import com.fges.rizomm.m1iii.learningagreementAPI.entity.LearningEntity;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.course.Course;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Form")
public class Form extends LearningEntity<Long> implements Serializable {
    private int schoolYearStart;
    private int schoolYearEnd;
    private short semester;
    private Date signatureDateStudent;
    private Date signatureDateRpiHost;
    private Date signatureDateRpiHome;
    private String signatureStudent;
    private String signatureRpiHost;
    private String signatureRpiHome;
    @OneToMany(mappedBy="form", cascade = CascadeType.ALL)
    private List<Course> courses;
    private String country;
    private String hostEstablishment;
    private String studyDomain;
    @ManyToOne
    private User student;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date lastModified;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isClosed;
    private String inviteToken;
    private String mailRpiHost;
}
