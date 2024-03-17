package com.nhnacademy.accountapi.security.details;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.accountapi.domain.User;
import com.nhnacademy.accountapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //TODO#4 - 회원 검증을 위해서 username(회원 아이디) 데이터베이스에 조회 합니다.
        //여러분의 쇼핑몰 구조에 맞게 데이터 베이스에서 조회하는 로직을 추가하세요.
        //또는 RestTemplate를 이용해서 shoppingmall-api 서버로 회원정보를 호출해서 PrincipalDetails 객체를 생성할 수도 있습니다.
        User user = userRepository.findByUsername(username)

        if (user == null) {
            throw new UsernameNotFoundException("사용자 이름을 찾을 수 없습니다. : " + username));
        }
        return new PrincipalDetails(user);
    }
    private User fetchUserFromShoppingMallAPI(String username) {
        // 쇼핑몰 API에서 회원 정보를 가져오는 HTTP 요청을 보냅니다.
        // 쇼핑몰 API에서 받은 응답 데이터를 User 객체로 매핑하여 반환합니다.
        // 이 코드는 실제 쇼핑몰 API와 통신하여 회원 정보를 가져오는 예시입니다.
        // 실제 쇼핑몰 API에 맞게 URL 및 데이터 형식을 수정해야 합니다.

        // 예시 코드:
        // String apiUrl = "http://shoppingmall-api.com/users/" + username;
        // String response = restTemplate.getForObject(apiUrl, String.class);
        // User user = objectMapper.readValue(response, User.class);
        // return user;

        return null; // 실제 코드로 대체해야 합니다.
    }

}
