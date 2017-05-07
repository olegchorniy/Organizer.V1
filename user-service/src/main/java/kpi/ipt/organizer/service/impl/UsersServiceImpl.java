package kpi.ipt.organizer.service.impl;

import kpi.ipt.organizer.model.User;
import kpi.ipt.organizer.repository.UsersRepository;
import kpi.ipt.organizer.service.UsersService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(String email, String password, String name) {
        User newUser = new User(0L, name, email, passwordEncoder.encode(password));
        return usersRepository.save(newUser);
    }

    @Override
    public boolean checkEmail(String email) {
        return usersRepository.findByEmail(email) == null;
    }
}
