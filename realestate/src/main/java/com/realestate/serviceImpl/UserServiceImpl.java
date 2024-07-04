package com.realestate.serviceImpl;

import com.realestate.dto.UserDto;
import com.realestate.entity.Roles;
import com.realestate.entity.User;
import com.realestate.entity.UserRole;
import com.realestate.exception.UserAlreadyExistException;
import com.realestate.repository.RolesRepo;
import com.realestate.repository.UserRepository;
import com.realestate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RolesRepo rolesRepo;


    @Override
    public void createUser(UserDto userDto) throws UserAlreadyExistException {

        User existingUser = userRepo.findByEmail(userDto.getEmail());


        if (existingUser != null) {
            System.out.println("User already exists");
            throw new UserAlreadyExistException("User already exists");
        } else {

            User user = new User(userDto);

            Set<UserRole> roles = new HashSet<>();
            Roles role = new Roles();
            role.setId(44);
            role.setName("NORMAL");

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            roles.add(userRole);


            for (UserRole ur : roles) {
                this.rolesRepo.save(ur.getRole());
            }

            user.getUserRoles().addAll(roles);


            User registeredUser = userRepo.save(user);


            /*return new UserDto(registeredUser);*/
        }

    }
}
