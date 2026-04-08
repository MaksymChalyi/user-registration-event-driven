package com.maksimkaxxl.userregistrationeventdriven.mapper;

import com.maksimkaxxl.userregistrationeventdriven.dto.UserDto;
import com.maksimkaxxl.userregistrationeventdriven.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}