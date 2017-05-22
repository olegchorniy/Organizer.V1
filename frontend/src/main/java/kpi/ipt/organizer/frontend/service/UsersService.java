package kpi.ipt.organizer.frontend.service;

import kpi.ipt.organizer.frontend.model.rest.users.AuthResponse;

public interface UsersService {

    AuthResponse login(String mail, String password);
}