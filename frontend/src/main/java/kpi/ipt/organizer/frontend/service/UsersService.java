package kpi.ipt.organizer.frontend.service;

import kpi.ipt.organizer.frontend.model.rest.users.AuthResponse;

public interface UsersService {

    void register(String name, String email, String password);

    AuthResponse login(String mail, String password);
}