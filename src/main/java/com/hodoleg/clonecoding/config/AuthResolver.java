package com.hodoleg.clonecoding.config;

import com.hodoleg.clonecoding.config.data.UserSession;
import com.hodoleg.clonecoding.domain.Session;
import com.hodoleg.clonecoding.exception.Unauthorized;
import com.hodoleg.clonecoding.respository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class AuthResolver implements HandlerMethodArgumentResolver {
    private final SessionRepository sessionRepository;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getGenericParameterType().equals(UserSession.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String accessToken = webRequest.getHeader("Authorization");
        if(accessToken == null || accessToken.equals("")){
            throw new Unauthorized();
        }
        // 데이터 베이스 사용자 확인작업
        Session seseion = sessionRepository.findByAccessToken(accessToken)
                .orElseThrow(Unauthorized::new);
        return new UserSession(seseion.getAuthUser().getId());
    }
}
