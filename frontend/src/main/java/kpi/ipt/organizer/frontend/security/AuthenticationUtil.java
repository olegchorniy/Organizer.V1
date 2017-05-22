package kpi.ipt.organizer.frontend.security;

import kpi.ipt.organizer.auth.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public abstract class AuthenticationUtil {

    private static final String USER_ID = "user.id";

    private AuthenticationUtil() {
    }

    public static void login(long userId) {
        getCurrentSession(true).setAttribute(USER_ID, userId);
    }

    public static void logout() {
        HttpSession currentSession = getCurrentSession(false);
        if (currentSession == null) {
            return;
        }

        currentSession.removeAttribute(USER_ID);
    }

    public static long getCurrentUserId() {
        HttpSession currentSession = getCurrentSession(false);
        if (currentSession == null) {
            throw new AuthenticationException();
        }

        Long userId = (Long) currentSession.getAttribute(USER_ID);
        if (userId == null) {
            throw new AuthenticationException();
        }

        return userId;
    }

    private static HttpSession getCurrentSession(boolean createNew) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getRequest().getSession(createNew);
    }
}