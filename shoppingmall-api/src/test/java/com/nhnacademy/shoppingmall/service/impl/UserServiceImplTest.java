package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.UserDto;
import com.nhnacademy.shoppingmall.enitiy.User;
import com.nhnacademy.shoppingmall.enums.Auth;
import com.nhnacademy.shoppingmall.exception.user.UserNotFoundException;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup(){

    }
    @Test
    @DisplayName("유저 조회 테스트")
    void getUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(User.builder().name("유저1").auth("ROLE_USER").build()));
        UserDto.Read.Response actual = userService.getUser(1L);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getName()).isEqualTo("유저1");
    }

    @Test
    @DisplayName("유저 조회 실패 테스트")
    void getUserFail() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> userService.getUser(1L))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found: " + 1);
    }


    @Test
    @DisplayName("유저 목록 조회 테스트")
    void getUserListPageByAuth() {
        Pageable pageable = PageRequest.of(0,9, Sort.Direction.DESC,"createdAt");
        List<User> userList = List.of(User.builder().name("유저1").auth("ROLE_USER").build()
                                    , User.builder().name("유저2").auth("ROLE_USER").build());
        when(userRepository.findPageByAuth(any(), anyString())).thenReturn(new PageImpl<>(userList, pageable, 2));
        Page<UserDto.Read.Response> actual = userService.getUserListPageByAuth(PageRequest.of(0, 10), Auth.ROLE_USER );

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getContent()).isNotNull();
        Assertions.assertThat(actual.getContent()).hasSize(2);
        Assertions.assertThat(actual.getContent().get(0).getName()).isEqualTo("유저1");
    }

}