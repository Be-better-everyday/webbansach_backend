package com.example.webbansach_backend.service;

import com.example.webbansach_backend.dao.AuthorityRepository;
import com.example.webbansach_backend.dao.UserRepository;
import com.example.webbansach_backend.entity.Authority;
import com.example.webbansach_backend.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class UserServiceImpl implements  UserService {
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public User findByUsername(String username) {
        System.out.println("Username = " + username);
        User byUsername = userRepository.findByUsername(username);
        System.out.println(byUsername);
        return byUsername;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(username == null) {
            throw new UsernameNotFoundException("The account is not exist!");
        }

        org.springframework.security.core.userdetails.User securityUser =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), toGrandtedAuthorities(user.getAuthorityList()));

        return securityUser;
    }

    private Collection<? extends GrantedAuthority> toGrandtedAuthorities(Collection<Authority> authorities){
        return authorities.stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .map(authority -> {
                    System.out.println("AuthorityName: " + authority.getAuthorityName());
                    return new SimpleGrantedAuthority(authority.getAuthorityName());
                })
                .collect(Collectors.toList());
    }
}
