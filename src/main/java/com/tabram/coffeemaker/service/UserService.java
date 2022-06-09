package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.RoleRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final CoffeeUserService coffeeUserService;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, CoffeeUserService coffeeUserService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.coffeeUserService = coffeeUserService;
        this.roleRepository = roleRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getUserName(), new BCryptPasswordEncoder().encode(registrationDto.getPassword()), true);

        if (roleRepository.findByName("ROLE_USER") == null) {
            user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        } else {
            user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        }

        userRepository.saveAndFlush(user);
        coffeeUserService.addCoffeeListToUser(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user.isEnabled(), true, true, true, mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Transactional
    public void deactivationUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist"));
        user.setEnabled(!user.isEnabled());
    }
}
