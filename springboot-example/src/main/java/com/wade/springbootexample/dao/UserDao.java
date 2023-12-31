package com.wade.springbootexample.dao;

import com.wade.springbootexample.dto.UserRegisterRequest;
import com.wade.springbootexample.model.User;

public interface UserDao {
    User getUserById(Integer userId);
    User getUserByEmail(String email);
    Integer createUser(UserRegisterRequest userRegisterRequest);

}
