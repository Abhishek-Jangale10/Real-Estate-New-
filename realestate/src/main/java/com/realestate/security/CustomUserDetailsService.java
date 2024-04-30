package com.realestate.security;

import com.realestate.entity.User;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.UserDisabledException;
import com.realestate.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User users = this.usersRepository.findByEmail(email);
        if (users == null) {
            System.out.println("user not found");
            try {
                throw new ResourceNotFoundException("user not found","email",email);
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else if(!users.isEnabled()){
            System.out.println("user is disabled");
            try {
                throw new UserDisabledException("User is disabled");
            } catch (UserDisabledException e) {
                throw new RuntimeException(e);
            }
        }

        return users;
    }
}
