package com.example.spaceshipapi.service;

import com.example.spaceshipapi.entity.Spaceship;
import com.example.spaceshipapi.repository.SpaceshipRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
public class SpaceshipServiceTest {
    @Mock
    private SpaceshipRepository repository;

    @InjectMocks
    private SpaceshipService service;

    public SpaceshipServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSpaceshipById() {
        Spaceship spaceship = new Spaceship(1L,"X-Wing", "Star Wars");
        when(repository.findById(1L)).thenReturn(Optional.of(spaceship));

        Optional<Spaceship> found = service.getSpaceshipById(1L);

        assertEquals("X-Wing", found.get().getName());
        assertEquals("Star Wars", found.get().getSeries());
    }
}
