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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto.Read.Response> getUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @GetMapping("/login/{loginId}")
    public ResponseEntity<UserDto.Read.Response> getUserByLoginId(@PathVariable String loginId){
        return ResponseEntity.ok().body(userService.getUserByLoginId(loginId));
    }


    @PostMapping
    public ResponseEntity<UserDto.Create.Response> createUser(@RequestBody UserDto.Create.Request request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }
    @GetMapping
    public ResponseEntity<Page<UserDto.Read.Response>> getUserListPage(@ParameterObject @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                       @RequestParam(value = "auth") Auth auth){
        return ResponseEntity.ok().body(userService.getUserListPageByAuth(pageable, auth));
    }
}
