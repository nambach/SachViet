package io.nambm.sachviet.service;

import io.nambm.sachviet.entity.User;

public interface UserService {

    User checkLogin(String username, String password);
}
