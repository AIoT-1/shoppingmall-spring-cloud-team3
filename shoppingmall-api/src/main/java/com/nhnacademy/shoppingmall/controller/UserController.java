package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.UserDto;
import com.nhnacademy.shoppingmall.enums.Auth;
import com.nhnacademy.shoppingmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto.Read.Response getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping
    public UserDto.Create.Response createUser(@RequestBody UserDto.Create.Request request){
        return userService.createUser(request);
    }
    @GetMapping
    public Page<UserDto.Read.Response> getUserListPage(@ParameterObject @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                       @RequestParam(value = "auth") Auth auth){
        return userService.getUserListPageByAuth(pageable, auth);
    }
}
