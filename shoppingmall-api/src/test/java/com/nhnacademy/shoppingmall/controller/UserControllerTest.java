package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.UserDto;
import com.nhnacademy.shoppingmall.enitiy.User;
import com.nhnacademy.shoppingmall.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("유저 조회 테스트")
    void getUser() throws Exception {
        when(userService.getUser(anyLong())).thenReturn(UserDto.Read.Response.fromEntity(User.builder().name("유저1").auth("ROLE_USER").build()));
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("유저1")))
                .andExpect(jsonPath("auth", equalTo("ROLE_USER")));
    }

    @Test
    @DisplayName("유저 목록 페이지 조회 테스트")
    void getUserListPage() throws Exception {

        mockMvc.perform(get("/users")
                .param("auth", "admin"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("유저 목록 페이지 조회 테스트(없는 권한 요청)")
    void getUserListPageWithInvalidAuth() throws Exception {
        mockMvc.perform(get("/users")
                .param("auth", "XXXX"))
                .andExpect(status().isBadRequest());
    }
}