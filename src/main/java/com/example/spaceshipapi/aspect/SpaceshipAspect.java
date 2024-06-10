package com.example.spaceshipapi.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class SpaceshipAspect {
    @Pointcut("execution(* com.example.spaceshipapi.service.SpaceshipService.getSpaceshipById(..)) && args(id)")
    public void getSpaceshipById(Long id) {}

    @AfterReturning(pointcut = "getSpaceshipById(id)", returning = "result")
    public void logNegativeId(Long id, Optional<?> result) {
        if (id < 0) {
            System.out.println("Request for spaceship with negative id: " + id);
        }
    }
}
