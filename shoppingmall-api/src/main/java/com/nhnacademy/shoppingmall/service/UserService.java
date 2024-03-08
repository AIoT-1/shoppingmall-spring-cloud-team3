package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.PageResponseDto;
import com.nhnacademy.shoppingmall.dto.UserDto;
import com.nhnacademy.shoppingmall.enums.Auth;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto.Read.Response getUser(Long id);
    UserDto.Create.Response createUser(UserDto.Create.Request request);
    PageResponseDto<UserDto.Read.Response> getUserListPageByAuth(Pageable pageable, Auth auth);
    UserDto.UserDetails.Response getUserByLoginId(String loginId);
}
