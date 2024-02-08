package com.maxi.ecommerce.services.user;

import java.util.Optional;

import com.maxi.ecommerce.models.User;

public interface UserService {

    public User save(User user);

    public Optional<User> findByEmail(String email);

    public Optional<User> findById(Long id);
}
