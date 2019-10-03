package com.fges.rizomm.m1iii.learningagreementAPI.entity.course;

import com.fges.rizomm.m1iii.learningagreementAPI.entity.LearningEntity;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.form.Form;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Course")
public class Course extends LearningEntity<Long> implements Serializable {
    private String titleCourse;
    private int nbrCredit;
    private int nbrEcts;
    @ManyToOne
    private Form form;
}
