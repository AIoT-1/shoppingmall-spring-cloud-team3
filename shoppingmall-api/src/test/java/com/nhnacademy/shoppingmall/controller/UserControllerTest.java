package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.UserDto;
import com.nhnacademy.shoppingmall.enitiy.User;
import com.nhnacademy.shoppingmall.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    
    private final String URL = "/api/users";

    @Test
    @DisplayName("유저 조회 테스트")
    void getUser() throws Exception {
        when(userService.getUser(anyLong())).thenReturn(UserDto.Read.Response.fromEntity(User.builder().name("유저1").auth("ROLE_USER").build()));
        mockMvc.perform(get(URL +"/1")
                        .header("X-USER-ID", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", equalTo("유저1")))
                .andExpect(jsonPath("auth", equalTo("ROLE_USER")));
    }

    @Test
    @DisplayName("유저 목록 페이지 조회 테스트")
    void getUserListPage() throws Exception {

        mockMvc.perform(get(URL)
                        .header("X-USER-ID", "1")
                .param("auth", "admin"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("유저 목록 페이지 조회 테스트(없는 권한 요청)")
    void getUserListPageWithInvalidAuth() throws Exception {
        mockMvc.perform(get(URL)
                        .header("X-USER-ID", "1")
                .param("auth", "XXXX"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("유저 생성 테스트")
    void createUser() throws Exception {
        mockMvc.perform(post(URL)
                .header("X-USER-ID", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\": \"유저1\",\n" +
                                "  \"loginId\": \"user1\",\n" +
                                "  \"birthDate\": \"1991-01-01\",\n" +
                                "  \"password\": \"1234\",\n" +
                                "  \"auth\": \"ROLE_USER\"\n" +
                                "}")).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("유저 아이디로 조회 테스트")
    void getUserByLoginId() throws Exception {
        when(userService.getUserByLoginId("user1")).thenReturn(UserDto.UserDetails.Response.fromEntity(User.builder().loginId("유저1").auth("ROLE_USER").build()));
        mockMvc.perform(get(URL +"/user1/details")
                        .header("X-USER-ID", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("loginId", equalTo("유저1")))
                .andExpect(jsonPath("auth", equalTo("ROLE_USER")));
    }

}