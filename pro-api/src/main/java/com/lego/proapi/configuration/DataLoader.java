package com.lego.proapi.configuration;


import com.lego.proapi.domain.Bike;
import com.lego.proapi.domain.Role;
import com.lego.proapi.domain.User;
import com.lego.proapi.repository.BikeRepository;
import com.lego.proapi.repository.RoleRepository;
import com.lego.proapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class DataLoader implements CommandLineRunner {
    private final BikeRepository bikeRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(BikeRepository bikeRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.bikeRepository = bikeRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void loadUsersAndRoles() {
        List<Role> roles = Stream.of(
                Role
                        .builder()
                        .rolName("ADMIN")
                        .build(),
                Role
                        .builder()
                        .rolName("USER")
                        .build()
        )
                .map(roleRepository::save)
                .collect(Collectors.toList());
        User user = User
                .builder()
                .email("luisgranada1213@gmail.com")
                .userName("1213lego")
                .password(passwordEncoder.encode("1213lego"))
                .build();
        saveUserWithRoles(user, roles.get(0), roles.get(1));
        user = User
                .builder()
                .email("lgranada@soaint.com")
                .userName("lgranada")
                .password(passwordEncoder.encode("lgranada"))
                .build();
        saveUserWithRoles(user, roles.get(1));
    }

    public void saveUserWithRoles(User user, Role... roles) {
        user = userRepository.save(user);
        for (Role role : roles) {
            user.addRol(role);
        }
    }

    public void loadBikes() {
        List<Bike> bikes = IntStream.range(1, 5)
                .mapToObj((i) -> Bike.builder()
                        .price(i * 6.831698)
                        .serial("B" + i)
                        .weight(1.36598 * i * i)
                        .purchaseDate(LocalDate.now())
                        .build())
                .collect(Collectors.toList());
        bikeRepository.saveAll(bikes);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        loadBikes();
        loadUsersAndRoles();
    }
}
