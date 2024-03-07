package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.UserDto;
import com.nhnacademy.shoppingmall.enums.Auth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto.Read.Response getUser(Long id);
    UserDto.Create.Response createUser(UserDto.Create.Request request);
    Page<UserDto.Read.Response> getUserListPageByAuth(Pageable pageable, Auth auth);
}
