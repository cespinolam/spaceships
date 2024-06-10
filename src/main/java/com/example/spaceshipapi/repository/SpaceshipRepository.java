package com.example.spaceshipapi.repository;

import com.example.spaceshipapi.entity.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship, Long> {
    List<Spaceship> findByNameContainingIgnoreCase(String name);
}
