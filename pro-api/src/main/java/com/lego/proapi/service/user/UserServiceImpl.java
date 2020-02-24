package com.lego.proapi.service.user;

import com.lego.proapi.domain.User;
import com.lego.proapi.dto.model.UserDto;
import com.lego.proapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public static User ANONYMOUS_USER = User
            .builder()
            .email("anonymous@anonymous.com")
            .roles(List.of())
            .userName("anonymous")
            .build();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        userRepository.save(ANONYMOUS_USER);
    }

    @Override
    public UserDto signUp(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto login(UserDto userDto) {
        return null;
    }
}

