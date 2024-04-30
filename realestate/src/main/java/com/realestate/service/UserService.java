package com.realestate.service;

import com.realestate.dto.UserDto;
import com.realestate.exception.UserAlreadyExistException;

public interface UserService {
    public void createUser(UserDto userDto) throws UserAlreadyExistException;
}
