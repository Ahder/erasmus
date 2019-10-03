package com.fges.rizomm.m1iii.learningagreementAPI.services.user;

import com.fges.rizomm.m1iii.learningagreementAPI.components.SendMail;
import com.fges.rizomm.m1iii.learningagreementAPI.dto.user.UserDTO;
import com.fges.rizomm.m1iii.learningagreementAPI.entity.user.User;
import com.fges.rizomm.m1iii.learningagreementAPI.exception.userNotFound.UserNotFoundException;
import com.fges.rizomm.m1iii.learningagreementAPI.repository.user.UserRepository;
import com.fges.rizomm.m1iii.learningagreementAPI.services.SuperService;
import com.fges.rizomm.m1iii.learningagreementAPI.util.BCryptManagerUtil;
import com.fges.rizomm.m1iii.learningagreementAPI.util.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService extends SuperService<User,UserDTO>  implements IUserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptManagerUtil bcrypt;
    private final SendMail sendMail;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptManagerUtil bcrypt,
                       SendMail sendMail) {
        this.userRepository = userRepository;
        this.bcrypt = bcrypt;
        this.sendMail = sendMail;
    }

    public UserDTO entityToDto(User entity) {
        return super.getMapper().map(entity, UserDTO.class);
    }

    public User dtoToEntity(UserDTO dto) {
        return super.getMapper().map(dto, User.class);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
		List<RoleEnum> roleEnumList = Collections.singletonList(RoleEnum.STUDENT);
		userDTO.setRoles(roleEnumList);
		userDTO.setPassword(this.bcrypt.getPasswordEncoder().encode(userDTO.getPassword()));
        User user = this.dtoToEntity(userDTO);
        user.getSpinneret().setUser(user);
		return this.entityToDto(this.userRepository.save(user));
    }

    public UserDTO resetPassword(Long id, UserDTO userDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = this.userRepository.getOne(id);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return this.entityToDto(this.userRepository.save(user));
    }

    public UserDTO getByToken(String token) {
        User user = userRepository.findByResetToken(token);
        UserDTO userDto = null;
        if (user != null) {
            userDto = this.entityToDto(user);
        }
        return userDto;
    }

    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

	@Override
	public List<UserDTO> getUsers(Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();
        UserDTO userDTO = this.entityToDto(userDetails);
        switch (userDTO.getRole().toString()) {
            case "ADMIN":
                return this.entityListToDtoList(this.userRepository.findAll());
            case "RPI":
                return this.entityListToDtoList(this.userRepository.findAllByRpi(userDetails));
            case "STUDENT":
            default:
                return null;
        }
	}

	@Override
    public UserDTO findOne(Long id) {
        return this.entityToDto(this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Long id) {
        User newUser = this.dtoToEntity(userDto);
        return this.userRepository.findById(id).map(user -> {
            UserDTO previousUser = this.entityToDto(user);
            if (!bcrypt.passwordMatch(previousUser.getPassword(), newUser.getPassword())) {
                newUser.setPassword(this.bcrypt.getPasswordEncoder().encode(newUser.getPassword()));
            }
            return this.entityToDto(this.userRepository.save(newUser));
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserDTO passwordForgot(UserDTO userDto) {
        Optional<User> mayUser = this.userRepository.findByEmail(userDto.getEmail());
        if (mayUser.isPresent()) {
            User user = mayUser.get();
            user.setResetToken(Long.toString(System.currentTimeMillis()));
            UserDTO userDto1 = this.entityToDto(userRepository.save(user));
            this.sendMail.sendEmailForPasswordForgot(userDto1);
            return userDto1;
        } else {
            throw new UsernameNotFoundException("There is no user with this mail address in the database : " + userDto.getEmail());
        }
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

}
