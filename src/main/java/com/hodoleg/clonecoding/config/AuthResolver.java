package com.hodoleg.clonecoding.config;

import com.hodoleg.clonecoding.config.data.UserSession;
import com.hodoleg.clonecoding.domain.Session;
import com.hodoleg.clonecoding.exception.Unauthorized;
import com.hodoleg.clonecoding.respository.SessionRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;

@RequiredArgsConstructor
@Slf4j
public class AuthResolver implements HandlerMethodArgumentResolver {
    private static final String KEY = "e4c4bpT90UfRmmnmlS2Qt9p/O8VU2Xk2NKxVOeRNMVI=";
    private final SessionRepository sessionRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getGenericParameterType().equals(UserSession.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String jws = webRequest.getHeader("Authorization");
        if (jws == null) {
            log.error("httpServletRequest is null");
            throw new Unauthorized();
        }
        byte[] decodedKey = Base64.decodeBase64(KEY);
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(decodedKey)
                    .build()
                    .parseClaimsJws(jws);
            log.info(">> {}" + claimsJws);
            String userId = claimsJws.getBody().getSubject();

            return new UserSession(Long.parseLong(userId));
        } catch (JwtException e) {
            throw new Unauthorized();
        }
    }
}
