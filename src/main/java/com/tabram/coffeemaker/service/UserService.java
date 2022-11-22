package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.UserDto;
import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.exception.UserAlreadyExistAuthenticationException;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.RoleRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class UserService implements UserServiceInterface, UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CoffeeUserService coffeeUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, CoffeeUserService coffeeUserService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.coffeeUserService = coffeeUserService;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegistrationDto userRegistrationDto) {
        if (userRepository.existsByUsername(userRegistrationDto.getUsername())) {
            throw new UserAlreadyExistAuthenticationException("Username is already taken");
        } else {
            saveUser(userRegistrationDto);
            addRoleToUser(userRegistrationDto.getUsername(), "ROLE_USER");
            coffeeUserService.addCoffeeListToUser(getUserByUsername(userRegistrationDto.getUsername()));
            return getUserByUsername(userRegistrationDto.getUsername());
        }
    }

    @Override
    public User getUserByUsername(String username) {
        log.info("Fetching user: {}", username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found '" + username + "'");
        } else {
            return user;
        }
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User saveUser(UserRegistrationDto registrationDto) {
        log.info("Saving new user: {}", registrationDto.getUsername());
        User user = new User(registrationDto.getUsername(), passwordEncoder.encode(registrationDto.getPassword()), true);
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role: {}", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Add new role '{}' to user: {}", roleName, username);
        User user = getUserByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public void deactivationUser(String username) {
        User user = getUserByUsername(username);
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
    }

    public User updateUser(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        user.setRoles(userDto.getRoles());
        user.setEnabled(userDto.isEnabled());
        userRepository.save(user);
        return user;
    }

//    public User currentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return getUserByUsername(authentication.getName());
//    }
}
