package com.example.ims.authentication;

import com.example.ims.models.UserRepository;
import com.example.ims.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        com.example.ims.models.User user = userService.findUserByEmail(s);
        if(user == null){
            throw new UsernameNotFoundException("User not found with userid: " + s);
        }

        return new User(s, user.getPassword(),new ArrayList<>());
    }

}
