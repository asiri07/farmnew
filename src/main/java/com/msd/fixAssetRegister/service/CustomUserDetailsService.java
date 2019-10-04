package com.msd.fixAssetRegister.service;


import com.msd.fixAssetRegister.model.CustomUserDetails;
import com.msd.fixAssetRegister.model.User;
import com.msd.fixAssetRegister.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        userOptional.orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));


        Optional<CustomUserDetails> customUserDetails = userOptional.map(user -> new CustomUserDetails(user));
             CustomUserDetails customUserDetails1 = customUserDetails.get();
        return userOptional.map(CustomUserDetails::new).get();
    }
}
