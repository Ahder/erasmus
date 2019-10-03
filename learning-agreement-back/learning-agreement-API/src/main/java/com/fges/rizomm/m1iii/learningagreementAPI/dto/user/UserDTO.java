package com.fges.rizomm.m1iii.learningagreementAPI.dto.user;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fges.rizomm.m1iii.learningagreementAPI.dto.spinneret.SpinneretDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.spinneret.Spinneret;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.user.User;
import com.fges.rizomm.m1iii.learningagreementAPI.util.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO  {
	
	private Long id;

	private String email;

	private String firstname;

	private String lastname;

	@JsonIgnore
	private Collection<RoleEnum> roles;

	private SpinneretDTO spinneret;

	private RoleEnum role;

	private String password;

	private Date birthdate;

	private boolean enabled;

	private String resetToken;

	public void setRoles(Collection<RoleEnum> roles) {
		this.roles = roles;
		if(this.roles != null && this.roles.size() != 0) {
			this.role = (RoleEnum) this.roles.toArray()[0];
		}
	}

	public void setRole(RoleEnum role) {
		this.role = role;
		if(this.role != null) {
			this.roles = Collections.singletonList(role);
		}
	}

	public UserDTO(String email, String firstname, String lastname, RoleEnum role, boolean enabled, String password) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.roles = Collections.singletonList(role);
		this.enabled = enabled;
		this.password = password;
	}

	public UserDTO(Long idUser, String email, String firstname, String lastname, RoleEnum role, boolean enabled) {
		this.id = idUser;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.roles = Collections.singletonList(role);
		this.enabled = enabled;
	}

}

