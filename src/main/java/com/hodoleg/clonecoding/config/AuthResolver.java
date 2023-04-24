package com.hodoleg.clonecoding.config;

import com.hodoleg.clonecoding.config.data.UserSession;
import com.hodoleg.clonecoding.domain.Session;
import com.hodoleg.clonecoding.exception.Unauthorized;
import com.hodoleg.clonecoding.respository.SessionRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;

@RequiredArgsConstructor @Slf4j
public class AuthResolver implements HandlerMethodArgumentResolver {
    private final SessionRepository sessionRepository;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getGenericParameterType().equals(UserSession.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if(httpServletRequest == null){
            log.error("httpServletRequest is null");
            throw new Unauthorized();
        }

        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies.length == 0){
            log.error("cookies is null");
            throw new Unauthorized();
        }
        String accessToken = Arrays.stream(cookies).filter(e -> "SESSION".equals(e.getName())).
                findAny().orElseThrow(Unauthorized::new).getValue();
        // 데이터 베이스 사용자 확인작업
        Session seseion = sessionRepository.findByAccessToken(accessToken)
                .orElseThrow(Unauthorized::new);
        return new UserSession(seseion.getAuthUser().getId());
    }
}
