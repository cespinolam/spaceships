package com.example.spaceshipapi.controller;

import com.example.spaceshipapi.entity.Spaceship;
import com.example.spaceshipapi.repository.SpaceshipRepository;
import com.example.spaceshipapi.service.SpaceshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class SpaceshipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SpaceshipService spaceshipService;

    @InjectMocks
    private SpaceshipController spaceshipController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSpaceships() {
        Spaceship spaceship1 = new Spaceship(1L, "X-Wing", "Star Wars");
        Spaceship spaceship2 = new Spaceship(2L, "Enterprise", "Star Trek");
        List<Spaceship> spaceships = Arrays.asList(spaceship1, spaceship2);
        Page<Spaceship> page = new PageImpl<>(spaceships);

        when(spaceshipService.getAllSpaceships(any(Pageable.class))).thenReturn(page);

        Page<Spaceship> result = spaceshipController.getAllSpaceships(0, 10);
        assertEquals(2, result.getContent().size());
        verify(spaceshipService, times(1)).getAllSpaceships(any(Pageable.class));
    }

    @Test
    public void testGetSpaceshipById() {
        Spaceship spaceship = new Spaceship(1L, "X-Wing", "Star Wars");

        when(spaceshipService.getSpaceshipById(1L)).thenReturn(Optional.of(spaceship));

        ResponseEntity<Spaceship> response = spaceshipController.getSpaceshipById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(spaceship.getName(), response.getBody().getName());
    }

    @Test
    public void testSearchSpaceshipsByName() {
        Spaceship spaceship = new Spaceship(1L, "X-Wing", "Star Wars");
        List<Spaceship> spaceships = Arrays.asList(spaceship);

        when(spaceshipService.getSpaceshipsByName("X-Wing")).thenReturn(spaceships);

        List<Spaceship> result = spaceshipController.searchSpaceshipsByName("X-Wing");
        assertEquals(1, result.size());
        verify(spaceshipService, times(1)).getSpaceshipsByName("X-Wing");
    }

    @Test
    public void testCreateSpaceship() {
        Spaceship spaceship = new Spaceship(null, "X-Wing", "Star Wars");

        when(spaceshipService.createSpaceship(any(Spaceship.class))).thenReturn(new Spaceship(1L, "X-Wing", "Star Wars"));

        Spaceship createdSpaceship = spaceshipController.createSpaceship(spaceship);
        assertNotNull(createdSpaceship);
        assertEquals("X-Wing", createdSpaceship.getName());
    }

    @Test
    public void testUpdateSpaceship() {
        Spaceship spaceship = new Spaceship(1L, "X-Wing", "Star Wars");
        Spaceship updatedDetails = new Spaceship(null, "TIE Fighter", "Star Wars");

        when(spaceshipService.updateSpaceship(1L, updatedDetails)).thenReturn(Optional.of(new Spaceship(1L, "TIE Fighter", "Star Wars")));

        ResponseEntity<Spaceship> response = spaceshipController.updateSpaceship(1L, updatedDetails);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("TIE Fighter", response.getBody().getName());
    }

    @Test
    public void testDeleteSpaceship() {
        when(spaceshipService.deleteSpaceship(1L)).thenReturn(true);

        ResponseEntity<Object> response = spaceshipController.deleteSpaceship(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(spaceshipService, times(1)).deleteSpaceship(1L);
    }
}
