package com.maksimkaxxl.userregistrationeventdriven.mapper;

import com.maksimkaxxl.userregistrationeventdriven.dto.UserDto;
import com.maksimkaxxl.userregistrationeventdriven.entity.User;

public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}