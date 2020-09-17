package com.ohdocha.admin.config;

import com.ohdocha.admin.util.DochaMap;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Properties properties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            DochaMap memberSession = (DochaMap) request.getSession().getAttribute("LOGIN_SESSION");
            if (memberSession == null) {
                sendRedirect(request, response);
                return false;
            }
        } catch (Exception e) {
            sendRedirect(request, response);
            return false;
        }

        return true;
    }

    private void sendRedirect(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception ignored) {

        }
    }

}
