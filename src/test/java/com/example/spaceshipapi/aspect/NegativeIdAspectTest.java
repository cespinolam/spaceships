package com.example.spaceshipapi.aspect;

import com.example.spaceshipapi.controller.SpaceshipController;
import com.example.spaceshipapi.entity.Spaceship;
import com.example.spaceshipapi.repository.SpaceshipRepository;
import com.example.spaceshipapi.service.SpaceshipService;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Aspect
public class NegativeIdAspectTest {
    @Mock
    private SpaceshipService spaceshipService;

    @InjectMocks
    private SpaceshipController spaceshipController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNegativeIdAspect() {
        when(spaceshipService.getSpaceshipById(-1L)).thenReturn(Optional.empty());

        ResponseEntity<Spaceship> response = spaceshipController.getSpaceshipById(-1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Before("execution(* com.example.spaceshipapi.controller.SpaceshipController.getSpaceshipById(..)) && args(id)")
    public void logIfNegativeId(Long id) {
        if (id < 0) {
            System.out.println("Request for spaceship with negative ID: " + id);
        }
    }

}
