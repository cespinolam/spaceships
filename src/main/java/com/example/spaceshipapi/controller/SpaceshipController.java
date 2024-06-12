package com.example.spaceshipapi.controller;

import com.example.spaceshipapi.entity.Spaceship;
import com.example.spaceshipapi.service.KafkaProducerService;
import com.example.spaceshipapi.service.SpaceshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipController {

    @Autowired
    private SpaceshipService spaceshipService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping
    public Page<Spaceship> getAllSpaceships(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return spaceshipService.getAllSpaceships(pageable);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "spaceship", key = "#id")
    public ResponseEntity<Spaceship> getSpaceshipById(@PathVariable Long id) {
        return spaceshipService.getSpaceshipById(id)
                .map(spaceship -> new ResponseEntity<>(spaceship, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public List<Spaceship> searchSpaceshipsByName(@RequestParam String name) {
        return spaceshipService.getSpaceshipsByName(name);
    }

    @PostMapping
    public Spaceship createSpaceship(@RequestBody Spaceship spaceship) {
        return spaceshipService.createSpaceship(spaceship);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Spaceship> updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceshipDetails) {
        return spaceshipService.updateSpaceship(id, spaceshipDetails)
                .map(updatedSpaceship -> new ResponseEntity<>(updatedSpaceship, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSpaceship(@PathVariable Long id) {
        return spaceshipService.deleteSpaceship(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessageToKafkaTopic(@RequestParam String topic, @RequestBody Object payload) {
        kafkaProducerService.sendMessage(topic, payload);
        return new ResponseEntity<>("Message sent to Kafka topic", HttpStatus.OK);
    }
}
