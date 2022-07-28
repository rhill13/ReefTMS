package io.rookware.reefTMSCore.interceptor;

import io.rookware.reefTMSCore.util.JwtServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationServiceInterceptor implements HandlerInterceptor {

    private final JwtServices jwtServices = new JwtServices();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        if (requestUri.equals("/appuser/new") || requestUri.equals("/appuser/login")) {
            return true;
        }

        String jws = request.getHeader("Authorization");
        if (jws == null) {
            response.setStatus(400);
            return false;
        }

        Boolean isValidated = jwtServices.validateToken(jws);
        if (!isValidated) {
            response.setStatus(400);
            return false;
        }

        request.setAttribute("userId", jwtServices.getUserFromJwt(jws));
        return true;
    }
}
