package kpi.ipt.organizer.frontend.service.impl;

import kpi.ipt.organizer.frontend.client.UsersClient;
import kpi.ipt.organizer.frontend.model.rest.users.AuthRequest;
import kpi.ipt.organizer.frontend.model.rest.users.AuthResponse;
import kpi.ipt.organizer.frontend.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersClient usersClient;

    public UsersServiceImpl(UsersClient usersClient) {
        this.usersClient = usersClient;
    }

    @Override
    public AuthResponse login(String email, String password) {
        return usersClient.authenticate(new AuthRequest(email, password));
    }
}