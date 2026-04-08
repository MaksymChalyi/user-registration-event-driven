package com.maksimkaxxl.userregistrationeventdriven.mapper.impl;

import com.maksimkaxxl.userregistrationeventdriven.dto.UserDto;
import com.maksimkaxxl.userregistrationeventdriven.entity.User;
import com.maksimkaxxl.userregistrationeventdriven.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .middleName(dto.middleName())
                .email(dto.email())
                .phone(dto.phone())
                .build();
    }

    @Override
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getEmail(),
                user.getPhone()
        );
    }

}
