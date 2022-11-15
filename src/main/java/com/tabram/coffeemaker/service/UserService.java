package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.dto.UserDto;
import com.tabram.coffeemaker.dto.UserRegistrationDto;
import com.tabram.coffeemaker.model.Role;
import com.tabram.coffeemaker.model.User;
import com.tabram.coffeemaker.repository.RoleRepository;
import com.tabram.coffeemaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByName(String username) {
        return userRepository.findByUserName(username);
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public boolean checkIfTheUserExists(String username) {
        return userRepository.existsByUserName(username);
    }

    public User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean existsUser = userRepository.existsByUserName(authentication.getName());
        if (!existsUser) {
            throw new UsernameNotFoundException("User not found");
        }
        return userRepository.findByUserName(authentication.getName());
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getUserName(), new BCryptPasswordEncoder().encode(registrationDto.getPassword()), true, Set.of(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        boolean existsUser = userRepository.existsByUserName(username);
        if (!existsUser) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        User user = userRepository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


    public void deactivationUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + userId + " does not exist"));
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
    }

    public void updateUser(UserDto userDto) {
        User userDB = userRepository.findByUserName(userDto.getUsername());
        userDB.setRoles(userDto.getRoles());
        userDB.setEnabled(userDto.isEnabled());
        userRepository.save(userDB);
    }

}
