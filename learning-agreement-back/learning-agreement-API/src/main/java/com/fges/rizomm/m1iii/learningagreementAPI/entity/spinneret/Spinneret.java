package com.fges.rizomm.m1iii.learningagreementAPI.entity.spinneret;

import com.fges.rizomm.m1iii.learningagreementAPI.entity.LearningEntity;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spinneret")
public class Spinneret extends LearningEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String level;
    private String label;
    @OneToOne(mappedBy = "spinneret")
    private User user;

}
