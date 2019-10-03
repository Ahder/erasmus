package com.fges.rizomm.m1iii.learningagreementAPI.services.user;

import java.util.List;
import com.fges.rizomm.m1iii.learningagreementAPI.dto.user.UserDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.user.User;
import com.fges.rizomm.m1iii.learningagreementAPI.util.RoleEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {

	UserDTO updateUser(UserDTO userDto, Long id);

	List<UserDTO> getUsers(Authentication authentication);

	UserDTO findOne(Long id);

	UserDTO addUser(UserDTO userDTO);

	void deleteUser(Long id);

	UserDTO resetPassword(Long id, UserDTO userDto);

	UserDTO getByToken(String token);

	UserDTO passwordForgot(UserDTO userDto);

}
