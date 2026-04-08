package com.maksimkaxxl.userregistrationeventdriven.service.impl;

import com.maksimkaxxl.userregistrationeventdriven.dto.UserDto;
import com.maksimkaxxl.userregistrationeventdriven.entity.User;
import com.maksimkaxxl.userregistrationeventdriven.event.UserCreatedEvent;
import com.maksimkaxxl.userregistrationeventdriven.exeption.EmailAlreadyExistsException;
import com.maksimkaxxl.userregistrationeventdriven.exeption.PhoneAlreadyExistsException;
import com.maksimkaxxl.userregistrationeventdriven.exeption.UserNotFoundException;
import com.maksimkaxxl.userregistrationeventdriven.mapper.UserMapper;
import com.maksimkaxxl.userregistrationeventdriven.messaging.UserEventPublisher;
import com.maksimkaxxl.userregistrationeventdriven.repository.UserRepository;
import com.maksimkaxxl.userregistrationeventdriven.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserEventPublisher publisher;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        log.info("Creating user with email: {}", userDto.email());
        if (userRepository.existsByEmail(userDto.email())) {
            log.warn("Email already exists: {}", userDto.email());
            throw new EmailAlreadyExistsException(userDto.email());
        }

        if (userRepository.existsByPhone(userDto.phone())) {
            log.warn("Phone already exists: {}", userDto.phone());
            throw new PhoneAlreadyExistsException(userDto.phone());
        }

        User user = userMapper.toEntity(userDto);
        try {
            User savedUser = userRepository.save(user);
            log.info("User saved with id: {}", savedUser.getId());
            UserCreatedEvent event = new UserCreatedEvent(
                    savedUser.getId(),
                    savedUser.getFirstName(),
                    savedUser.getLastName(),
                    savedUser.getEmail(),
                    savedUser.getPhone(),
                    savedUser.getCreatedAt()
            );

            publisher.send(event);
            log.info("UserCreatedEvent sent for userId: {}", savedUser.getId());
            return userMapper.toDto(savedUser);

        } catch (DataIntegrityViolationException e) {
            log.error("DB constraint violation for email: {}, phone: {}", userDto.email(), userDto.phone(), e);
            String message = e.getMostSpecificCause().getMessage();
            if (message != null && message.contains("email")) {
                throw new EmailAlreadyExistsException(userDto.email());
            }
            if (message != null && message.contains("phone")) {
                throw new PhoneAlreadyExistsException(userDto.phone());
            }
            throw e;
        }
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        log.info("Fetching users page: {}", pageable);
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        log.info("Fetching user by email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("User not found: {}", email);
                    return new UserNotFoundException(email);
                });

        return userMapper.toDto(user);
    }

}