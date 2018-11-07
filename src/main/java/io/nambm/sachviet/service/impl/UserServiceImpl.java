package io.nambm.sachviet.service.impl;

import io.nambm.sachviet.entity.User;
import io.nambm.sachviet.repository.UserRepository;
import io.nambm.sachviet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User checkLogin(String username, String password) {
        User user;
        user = userRepository.checkLogin(username, password);
        return user;
    }
}
