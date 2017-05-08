package kpi.ipt.organizer.service;

import kpi.ipt.organizer.model.User;

public interface UsersService {

    User getUser(long userId);

    User createUser(String email, String password, String name);

    boolean checkEmail(String email);

    boolean authenticate(String email, String password);
}
