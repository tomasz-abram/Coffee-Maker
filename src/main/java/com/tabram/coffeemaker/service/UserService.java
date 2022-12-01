package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.UserDto;
import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.exception.UserAlreadyExistAuthenticationException;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
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

import java.util.*;


@Service
@Transactional
public class UserService implements UserServiceInterface, UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CoffeeUserService coffeeUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService, CoffeeUserService coffeeUserService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.coffeeUserService = coffeeUserService;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegistrationDto userRegistrationDto) {
        String username = userRegistrationDto.getUsername();
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistAuthenticationException("Username is already taken");
        } else {
            saveUser(userRegistrationDto);
            addRoleToUser(username, "ROLE_USER");
            User user = getUserByUsername(username);
            coffeeUserService.addCoffeeListToUser(user);
            return user;
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

    public User getUserById(long id) {
        log.info("Fetching user: {}", id);
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found '" + id + "'"));
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
    public void addRoleToUser(String username, String roleName) {
        log.info("Add new role '{}' to user: {}", roleName, username);
        User user = getUserByUsername(username);
        Role role = roleService.findRole(roleName);
        if (user.getRoles() == null) {
            Set<Role> userRole = new HashSet<>();
            userRole.add(role);
            user.setRoles(userRole);
        } else {
            Set<Role> userRoles = user.getRoles();
            userRoles.add(role);
        }
        userRepository.save(user);
    }

    public void deleteRoleFromUser(String username, String roleName) {
        log.info("Delete role '{}' from user: {}", roleName, username);
        User user = getUserByUsername(username);
        Set<Role> userRoles = user.getRoles();
        Role delRole = roleService.findRole(roleName);
        userRoles.remove(delRole);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public User updateUser(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        user.setRoles(userDto.getRoles());
        user.setEnabled(userDto.isEnabled());
        userRepository.save(user);
        return user;
    }

    public void deactivationUser(String username) {
        User user = getUserByUsername(username);
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
    }
}
