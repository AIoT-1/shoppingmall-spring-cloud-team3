package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.UserDto;
import com.nhnacademy.shoppingmall.enums.Auth;
import com.nhnacademy.shoppingmall.exception.user.UserNotFoundException;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto.Read.Response getUser(Long id) {

        return  userRepository.findById(id)
                .map(UserDto.Read.Response::fromEntity)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public UserDto.Create.Response createUser(UserDto.Create.Request request) {
        return null;
    }

    @Override
    public Page<UserDto.Read.Response> getUserListPageByAuth(Pageable pageable, Auth auth) {
        return userRepository.findPageByAuth(pageable, auth.name())
                             .map(UserDto.Read.Response::fromEntity);
    }
}