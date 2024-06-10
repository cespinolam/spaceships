package com.example.spaceshipapi.service;

import com.example.spaceshipapi.entity.Spaceship;
import com.example.spaceshipapi.repository.SpaceshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceshipService {
    @Autowired
    private SpaceshipRepository repository;

    public Page<Spaceship> getAllSpaceships(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Cacheable("spaceship")
    public Optional<Spaceship> getSpaceshipById(Long id) {
        return repository.findById(id);
    }

    public List<Spaceship> getSpaceshipsByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Spaceship createSpaceship(Spaceship spaceship) {
        return repository.save(spaceship);
    }

    public Spaceship updateSpaceship(Long id, Spaceship spaceship) {
        spaceship.setId(id);
        return repository.save(spaceship);
    }

    public void deleteSpaceship(Long id) {
        repository.deleteById(id);
    }
}
