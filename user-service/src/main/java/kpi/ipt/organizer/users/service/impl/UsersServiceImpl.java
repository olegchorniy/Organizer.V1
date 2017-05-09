package kpi.ipt.organizer.users.service.impl;

import kpi.ipt.organizer.users.exceptions.IllegalUserParametersException;
import kpi.ipt.organizer.users.model.User;
import kpi.ipt.organizer.users.repository.UsersRepository;
import kpi.ipt.organizer.users.service.UsersService;
import org.springframework.dao.DataIntegrityViolationException;
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
    public User getUser(long userId) {
        return usersRepository.findOne(userId);
    }

    @Override
    public User createUser(String email, String password, String name) {
        User newUser = new User(name, email, passwordEncoder.encode(password));

        try {
            return usersRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalUserParametersException(e);
        }
    }

    @Override
    public boolean checkEmail(String email) {
        return usersRepository.findByEmail(email) == null;
    }

    @Override
    public boolean authenticate(String email, String password) {
        User user = usersRepository.findByEmail(email);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}
