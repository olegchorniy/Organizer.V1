package kpi.ipt.organizer.frontend.security;

import kpi.ipt.organizer.auth.AuthenticationException;
import kpi.ipt.organizer.frontend.model.rest.users.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public abstract class AuthenticationUtil {

    private static final String USER = "user";

    private AuthenticationUtil() {
    }

    public static void login(User user) {
        getCurrentSession(true).setAttribute(USER, user);
    }

    public static void logout() {
        HttpSession currentSession = getCurrentSession(false);
        if (currentSession == null) {
            return;
        }

        currentSession.removeAttribute(USER);
    }

    public static boolean isAuthenticated() {
        HttpSession currentSession = getCurrentSession(false);
        if (currentSession == null) {
            return false;
        }

        User user = (User) currentSession.getAttribute(USER);
        return user != null;
    }

    public static User getCurrentUser() {
        HttpSession currentSession = getCurrentSession(false);
        if (currentSession == null) {
            throw new AuthenticationException();
        }

        User user = (User) currentSession.getAttribute(USER);
        if (user == null) {
            throw new AuthenticationException();
        }

        return user;
    }

    private static HttpSession getCurrentSession(boolean createNew) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getRequest().getSession(createNew);
    }
}