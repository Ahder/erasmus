package com.fges.rizomm.m1iii.learningagreementAPI.entity.user;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.fges.rizomm.m1iii.learningagreementAPI.entity.form.Form;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.spinneret.Spinneret;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.fges.rizomm.m1iii.learningagreementAPI.entity.LearningEntity;
import com.fges.rizomm.m1iii.learningagreementAPI.util.BCryptManagerUtil;
import com.fges.rizomm.m1iii.learningagreementAPI.util.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "USERS")
public class User extends LearningEntity<Long> implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Ignore
    private String username;

    private String firstname;

    private String lastname;

    private String email;

    private String urlSignature;

    private String resetToken;

    @ManyToOne
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name="rpi_id")
    private User rpi;

    @OneToMany(mappedBy="rpi")
    private Set<User> userList = new HashSet<>();

    @OneToOne(orphanRemoval = true)
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "spinneret_id", referencedColumnName = "id")
    private Spinneret spinneret;

    @JsonIgnore
    private String password;

    private Date birthdate;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @Cascade(value = CascadeType.REMOVE)
    @JoinTable(indexes = {
            @Index(name = "INDEX_USER_ROLE", columnList = "id_user") }, name = "roles", joinColumns = @JoinColumn(name = "id_user"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<RoleEnum> roles;

    @Column(name = "account_non_expired")
    @JsonIgnore
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    @JsonIgnore
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    @JsonIgnore
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    @JsonIgnore
    private boolean enabled;

    private boolean passHasBeenSet;

    /*@OneToMany(mappedBy = "student", cascade = javax.persistence.CascadeType.ALL)
    private Set<Form> forms;
    */
    public User() {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.passHasBeenSet = true;
        this.roles = Collections.singletonList(RoleEnum.ADMIN);
    }

    public User(String email, String firstname, String lastname, Collection<RoleEnum> roles) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.passHasBeenSet = true;
        this.roles = roles;
    }

    public User(String email, String password, String firstname, String lastname, Collection<RoleEnum> roles) {
        this.email = email;
        this.password = BCryptManagerUtil.passwordencoder().encode(password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.passHasBeenSet = true;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = StringUtils.collectionToCommaDelimitedString(getRoles().stream()
                .map(Enum::name).collect(Collectors.toList()));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

}
