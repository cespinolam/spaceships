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
    private SpaceshipRepository spaceshipRepository;

    public Page<Spaceship> getAllSpaceships(Pageable pageable) {
        return spaceshipRepository.findAll(pageable);
    }

    @Cacheable("spaceship")
    public Optional<Spaceship> getSpaceshipById(Long id) {
        return spaceshipRepository.findById(id);
    }

    public List<Spaceship> getSpaceshipsByName(String name) {
        return spaceshipRepository.findByNameContainingIgnoreCase(name);
    }

    public Spaceship createSpaceship(Spaceship spaceship) {
        return spaceshipRepository.save(spaceship);
    }

    public Optional<Spaceship> updateSpaceship(Long id, Spaceship spaceshipDetails) {
        return spaceshipRepository.findById(id)
                .map(spaceship -> {
                    spaceship.setName(spaceshipDetails.getName());
                    spaceship.setSeries(spaceshipDetails.getSeries());
                    return spaceshipRepository.save(spaceship);
                });
    }


    public boolean deleteSpaceship(Long id) {
        return spaceshipRepository.findById(id)
                .map(spaceship -> {
                    spaceshipRepository.delete(spaceship);
                    return true;
                }).orElse(false);
    }
}
