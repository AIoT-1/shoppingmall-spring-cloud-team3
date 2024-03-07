package com.nhnacademy.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

@Component
@ConfigurationProperties("jwt")
@Slf4j
public class JwtAuthorizationHeaderFilter extends AbstractGatewayFilterFactory<JwtAuthorizationHeaderFilter.Config> {

    private String secret;

    public JwtAuthorizationHeaderFilter() {
        super(Config.class);
    }

    public static class Config {
        // application.properties 파일에서 지정한 filer의 Argument값을 받는 부분
    }

    @Override
    public GatewayFilter apply(Config config) {
       return  (exchange, chain)-> {
           //TODO#3 JWT 검증 필터입니다.
           log.debug("jwt-validation-filter");

           ServerHttpRequest request = exchange.getRequest();

           // 헤더에서 Authorization 키의 값을 가져옴.
           String authorizationHeaader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

           // Authorization 헤더가 없거나 "bearer"로 시작하지 않은 경우, 401unauthorized 에러 반환
           if (authorizationHeaader == null || !authorizationHeaader.startsWith("Bearer")) {
               exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
               return exchange.getResponse().setComplete();
           }

           //TODO#3-1 Header에 Authorization 존재하지 않는다면 적절한 예외처리를 합니다.
           //인증 헤더에서 jwt 토큰 가져온다.(내가작성)
           String jwtToken = authorizationHeaader.substring("Bearer".length()).trim();

           //TODO#3-2 AccessToken jjwt 라이브러리를 사용하여 검증 구현하기
           try {
               // jwt 토큰을 검증하고 클레임을 추출한 내용
               Claims claims = Jwts.parserBuilder()
                       .setSigningKey(secret.getBytes())
                       .build()
                       .parseClaimsJws(jwtToken)
                       .getBody();

               // key값은 있는데 null이거나 비어있을 떄 예외처리
               // Bearer {Token}
               // {Token} 만 가져와서  jjwt 해서 검증을 할 수 있도록

               //이미 Token이 만료되었는지?
               //Token의 signature 값 검증(HMAC)
               //이미 로그아웃된 Token 인지? - Black List 관리
               //account-api의 JwtProperties를 참고하여 구현합니다.


               //TODO#3-3 검증이 완료되면  Request header에 X-USER-ID를 등록합니다.
               //exchange.getRequest().getHeaders(); <-- imutable 합니다. 즉 수정 할 수 없습니다.
               //exchage.mutate()를 이용해야 합니다. 아래 코드를 참고하세요.
               String userId = claims.get("userId", String.class);
               exchange.getRequest().mutate()
                       .header("X-USER-ID", userId)
                       .build();
           } catch (Exception e) {
               // 토큰 유효하지 않으면 예외처리
               exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
               return exchange.getResponse().setComplete();
           }
           // 다음 필터 요청 전달
           return chain.filter(exchange);
       };
    }
}

// memo장
// 요청 헤더에 X-USER-ID를 추가하는 필터를 구현한 것
//product-main-api
//shoppingmall-api
