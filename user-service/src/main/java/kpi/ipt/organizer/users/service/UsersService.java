package kpi.ipt.organizer.users.service;

import kpi.ipt.organizer.users.model.User;

public interface UsersService {

    User getUser(long userId);

    User registerUser(String email, String password, String name);

    boolean checkEmail(String email);

    boolean authenticate(String email, String password);
}
