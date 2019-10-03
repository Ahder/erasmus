package com.fges.rizomm.m1iii.learningagreementAPI.provider;

import com.fges.rizomm.m1iii.learningagreementAPI.services.user.UserService;
import com.fges.rizomm.m1iii.learningagreementAPI.util.BCryptManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class AppAuthProvider extends DaoAuthenticationProvider {
    @Autowired
    UserService userDetailsService;

    @Autowired
    BCryptManagerUtil bcrypt;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String name = auth.getName();
        String password = auth.getCredentials().toString();
        UserDetails user = userDetailsService.loadUserByUsername(name);
        if (user != null) {
            if (bcrypt.passwordMatch(password, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            }
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        } else {
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}