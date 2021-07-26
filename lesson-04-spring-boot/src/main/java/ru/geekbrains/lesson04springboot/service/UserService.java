package ru.geekbrains.lesson04springboot.service;

import org.springframework.data.domain.Page;

import ru.geekbrains.lesson04springboot.controller.UserDto;
import ru.geekbrains.lesson04springboot.controller.UserListParams;
import ru.geekbrains.lesson04springboot.persist.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Page<UserDto> findWithFilter(UserListParams userListParams);

    Optional<UserDto> findById(Long id);

    void save(UserDto user);

    void deleteById(Long id);
}
