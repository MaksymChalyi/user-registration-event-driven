package com.maksimkaxxl.userregistrationeventdriven.service.impl;

import com.maksimkaxxl.userregistrationeventdriven.dto.UserDto;
import com.maksimkaxxl.userregistrationeventdriven.entity.User;
import com.maksimkaxxl.userregistrationeventdriven.mapper.UserMapper;
import com.maksimkaxxl.userregistrationeventdriven.repository.UserRepository;
import com.maksimkaxxl.userregistrationeventdriven.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.email())) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        if (userRepository.existsByPhone(userDto.phone())) {
            throw new IllegalArgumentException("User with this phone already exists");
        }

        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return userMapper.toDto(user);
    }
}