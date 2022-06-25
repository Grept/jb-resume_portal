package com.grept.resumeportal.security;

import com.grept.resumeportal.models.MyUserDetails;
import com.grept.resumeportal.models.User;
import com.grept.resumeportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);

        userOptional.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return userOptional.map(MyUserDetails::new).get();
    }
}
