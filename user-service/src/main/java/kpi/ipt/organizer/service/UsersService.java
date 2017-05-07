package kpi.ipt.organizer.service;

import kpi.ipt.organizer.model.User;

public interface UsersService {

    User registerUser(String email, String password, String name);

    boolean checkEmail(String email);
}
