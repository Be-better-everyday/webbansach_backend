//package com.example.webbansach_backend.entity;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.*;
//
//public class MyUserDetails implements UserDetails {
//
//    private final User user;
//
//    public MyUserDetails(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Authority> authoritySet = new HashSet<>(user.getAuthorityList());
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        for (Authority authority : authoritySet) {
//            authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
//        }
//
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return user.isEnabled();
//    }
//
//}
