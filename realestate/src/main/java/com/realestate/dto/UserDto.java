package com.realestate.dto;

import com.realestate.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer userId;
    private String contactNo;
    private String email;
    private String fullName;
    private String password;

    public UserDto(User user){
        this.userId = user.getUserId();
        this.contactNo = user.getContactNo();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.password = user.getPassword();

    }
}
