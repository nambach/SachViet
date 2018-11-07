package io.nambm.sachviet.repository;

import io.nambm.sachviet.entity.User;
import io.nambm.sachviet.repository.generic.GenericRepository;

public interface UserRepository extends GenericRepository<User> {

    User checkLogin(String username, String password);
}
