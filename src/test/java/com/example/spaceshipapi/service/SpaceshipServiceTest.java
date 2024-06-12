package com.example.spaceshipapi.service;

import com.example.spaceshipapi.entity.Spaceship;
import com.example.spaceshipapi.repository.SpaceshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class SpaceshipServiceTest {
    @Mock
    private SpaceshipRepository spaceshipRepository;

    @InjectMocks
    private SpaceshipService spaceshipService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindSpaceshipById() {
        Spaceship spaceship = new Spaceship(1L, "X-Wing", "Star Wars");

        when(spaceshipRepository.findById(1L)).thenReturn(Optional.of(spaceship));

        Optional<Spaceship> result = spaceshipService.getSpaceshipById(1L);
        assertNotNull(result);
        assertEquals("X-Wing", result.get().getName());
    }
}
