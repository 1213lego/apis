package com.lego.proapi.configuration.security;

import com.lego.proapi.domain.User;
import com.lego.proapi.domain.UserRole;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
@ToString
public class CustomUserDetail implements UserDetails {
    private final User user;
    private Collection<GrantedAuthority> authorities;

    public CustomUserDetail(User user) {
        this.user = user;
        loadAuthorities();
    }

    private void loadAuthorities() {
        this.authorities = user
                .getRoles()
                .stream()
                .map(UserRole::getRole)
                .map(rol -> new SimpleGrantedAuthority("ROLE_"+rol.getRolName()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
