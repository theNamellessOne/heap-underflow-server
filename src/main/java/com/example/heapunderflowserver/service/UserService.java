package com.example.heapunderflowserver.service;

import com.example.heapunderflowserver.model.User;
import com.example.heapunderflowserver.repository.JpaRepository;
import com.example.heapunderflowserver.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends JpaService<User> implements UserDetailsService {
    public UserService(JpaRepository<User> repository) {
        super(repository);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return ((UserRepository) repository).findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
