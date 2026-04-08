package com.maksimkaxxl.userregistrationeventdriven.service;

import com.maksimkaxxl.userregistrationeventdriven.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto createUser(UserDto userDto);

    Page<UserDto> getAllUsers(Pageable pageable);

    UserDto getUserByEmail(String email);
}