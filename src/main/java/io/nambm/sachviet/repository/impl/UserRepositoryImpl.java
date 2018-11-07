package io.nambm.sachviet.repository.impl;

import io.nambm.sachviet.entity.User;
import io.nambm.sachviet.repository.UserRepository;
import io.nambm.sachviet.repository.generic.impl.GenericRepositoryImpl;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User checkLogin(String username, String password) {
        User tmp = new User(username, "", "", "");

        User user = findById(tmp);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }
}
