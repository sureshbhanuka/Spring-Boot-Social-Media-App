package com.socialmediaapp.service;

import com.socialmediaapp.dto.UserRegistrationDto;
import com.socialmediaapp.model.User;

public interface UserService {
    User registerUser(UserRegistrationDto dto);
    User findByEmail(String email);
}
