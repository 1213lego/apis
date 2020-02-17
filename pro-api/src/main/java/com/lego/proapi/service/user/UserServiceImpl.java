package com.lego.proapi.service.user;

import com.lego.proapi.domain.User;
import com.lego.proapi.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public Class<User> getEntityType() {
        return User.class;
    }

    @Override
    public void delete(User entity) {

    }
}

