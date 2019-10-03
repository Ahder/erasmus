package com.fges.rizomm.m1iii.learningagreementAPI.controller.user;

import com.fges.rizomm.m1iii.learningagreementAPI.dto.user.UserDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
	@Autowired
	IUserService userService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTO> findAllUsers(Authentication authentication) {
		return this.userService.getUsers(authentication);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO findOneUser(@PathVariable Long id) {
		return this.userService.findOne(id);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO addUser(@RequestBody UserDTO userDTO) {
		return this.userService.addUser(userDTO);
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO editUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
		return this.userService.updateUser(userDTO, id);
	}

	@PostMapping("/passwordForgot")
	public UserDTO passwordForgot(@RequestBody UserDTO user) {
		return this.userService.passwordForgot(user);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUser(@PathVariable Long id) {
		this.userService.deleteUser(id);
	}

	@GetMapping("/{token}")
	public UserDTO getByToken(@PathVariable String token) {
		return this.userService.getByToken(token);
	}

	@PutMapping("/resetPassword/{id}")
	public UserDTO resetPassword(@PathVariable Long id, @RequestBody UserDTO userDto) {
		return this.userService.resetPassword(id, userDto);
	}

}
