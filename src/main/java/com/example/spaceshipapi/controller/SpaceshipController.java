package com.example.spaceshipapi.controller;

import com.example.spaceshipapi.entity.Spaceship;
import com.example.spaceshipapi.service.SpaceshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipController {

    @Autowired
    private SpaceshipService service;

    @GetMapping
    public Page<Spaceship> getAllSpaceships(Pageable pageable) {
        return service.getAllSpaceships(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spaceship> getSpaceshipById(@PathVariable Long id) {
        return service.getSpaceshipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Spaceship> getSpaceshipsByName(@RequestParam String name) {
        return service.getSpaceshipsByName(name);
    }

    @PostMapping
    public Spaceship createSpaceship(@RequestBody Spaceship spaceship) {
        return service.createSpaceship(spaceship);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Spaceship> updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceship) {
        return ResponseEntity.ok(service.updateSpaceship(id, spaceship));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpaceship(@PathVariable Long id) {
        service.deleteSpaceship(id);
        return ResponseEntity.noContent().build();
    }
}
