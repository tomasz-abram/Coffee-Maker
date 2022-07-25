package com.tabram.coffeemaker.service;

import com.tabram.coffeemaker.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;
    private RoleService underTest;

    @BeforeEach
    void setUp() {
        underTest = new RoleService(roleRepository);
    }

    @Test
    void getAllRoles() {
        //when
        underTest.getAllRoles();
        //then
        verify(roleRepository).findAll();
    }
}