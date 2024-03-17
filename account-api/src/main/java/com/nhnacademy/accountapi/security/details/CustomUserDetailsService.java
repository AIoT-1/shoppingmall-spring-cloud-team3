package com.nhnacademy.accountapi.security.details;

import com.nhnacademy.accountapi.domain.User;
import com.nhnacademy.accountapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //TODO#4 - 회원 검증을 위해서 username(회원 아이디) 데이터베이스에 조회 합니다.
        //여러분의 쇼핑몰 구조에 맞게 데이터 베이스에서 조회하는 로직을 추가하세요.
        //또는 RestTemplate를 이용해서 shoppingmall-api 서버로 회원정보를 호출해서 PrincipalDetails 객체를 생성할 수도 있습니다.
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 이름을 찾을 수 없습니다. : " + username));
        return new PrincipalDetails(user);

    }

}
