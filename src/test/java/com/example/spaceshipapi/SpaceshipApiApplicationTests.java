package com.example.spaceshipapi;

import com.example.spaceshipapi.entity.Spaceship;
import com.example.spaceshipapi.repository.SpaceshipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class SpaceshipApiApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpaceshipRepository spaceshipRepository;

    @Test
    void getAllSpaceships() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/spaceships")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void testCreateSpaceship() {
        // Given
        Spaceship spaceship = new Spaceship();
        spaceship.setName("Millennium Falcon");
        spaceship.setSeries("Star Wars");

        // When
        Spaceship savedSpaceship = spaceshipRepository.save(spaceship);

        // Then
        assertThat(savedSpaceship).isNotNull();
        assertThat(savedSpaceship.getId()).isNotNull();
        assertThat(savedSpaceship.getName()).isEqualTo("Millennium Falcon");
        assertThat(savedSpaceship.getSeries()).isEqualTo("Star Wars");
    }
}
