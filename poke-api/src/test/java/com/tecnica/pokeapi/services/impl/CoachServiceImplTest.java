package com.tecnica.pokeapi.services.impl;

import com.tecnica.pokeapi.config.PokeApiProperties;
import com.tecnica.pokeapi.dto.RequestCoachDto;
import com.tecnica.pokeapi.dto.ResponseCoachDto;
import com.tecnica.pokeapi.exceptions.ResourceNotFoundException;
import com.tecnica.pokeapi.model.CoachEntity;
import com.tecnica.pokeapi.repository.CoachRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoachServiceImplTest {

    @Mock
    private CoachRepository coachRepository;

    @Mock
    private PokeApiProperties pokeApiProperties;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CoachServiceImpl coachService;

    @BeforeEach
    void setUp() {
        lenient().when(pokeApiProperties.getBaseUrl()).thenReturn("https://pokeapi.co/api/v2");
    }

    @Test
    void createCoach_ValidPokemon_Success() {
        RequestCoachDto requestCoachDto = new RequestCoachDto("Ash", "Pallet Town", 95.5f, List.of("pikachu"));
        CoachEntity coachEntity = new CoachEntity();
        when(restTemplate.getForObject(anyString(), eq(Object.class))).thenReturn(new Object());
        when(coachRepository.save(any(CoachEntity.class))).thenReturn(coachEntity);

        ResponseCoachDto responseCoachDto = coachService.createCoach(requestCoachDto);

        assertNotNull(responseCoachDto);
        verify(coachRepository, times(1)).save(any(CoachEntity.class));
    }

    @Test
    void createCoach_InvalidPokemon_ThrowsException() {
        RequestCoachDto requestCoachDto = new RequestCoachDto("Ash", "Pallet Town", 95.5f, List.of("invalidPokemon"));
        when(restTemplate.getForObject(anyString(), eq(Object.class))).thenThrow(new RestClientException("Not Found"));

        assertThrows(ResourceNotFoundException.class, () -> coachService.createCoach(requestCoachDto));
    }

    @Test
    void updateCoach_ValidId_Success() {
        Integer id = 1;
        RequestCoachDto requestCoachDto = new RequestCoachDto("Ash", "Pallet Town", 95.5f, List.of("pikachu"));
        CoachEntity coachEntity = new CoachEntity();
        when(coachRepository.findById(id)).thenReturn(Optional.of(coachEntity));
        when(coachRepository.save(any(CoachEntity.class))).thenReturn(coachEntity);

        ResponseCoachDto responseCoachDto = coachService.updateCoach(id, requestCoachDto);

        assertNotNull(responseCoachDto);
        verify(coachRepository, times(1)).save(any(CoachEntity.class));
    }

    @Test
    void updateCoach_InvalidId_ThrowsException() {
        Integer id = 1;
        RequestCoachDto requestCoachDto = new RequestCoachDto("Ash", "Pallet Town", 95.5f, List.of("pikachu"));
        when(coachRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> coachService.updateCoach(id, requestCoachDto));
    }

    @Test
    void getCoach_ValidId_Success() {
        Integer id = 1;
        CoachEntity coachEntity = new CoachEntity();
        when(coachRepository.findById(id)).thenReturn(Optional.of(coachEntity));

        ResponseCoachDto responseCoachDto = coachService.getCoach(id);

        assertNotNull(responseCoachDto);
    }

    @Test
    void getCoach_InvalidId_ThrowsException() {
        Integer id = 1;
        when(coachRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> coachService.getCoach(id));
    }

    @Test
    void deleteCoach_ValidId_Success() {
        Integer id = 1;
        CoachEntity coachEntity = new CoachEntity();
        when(coachRepository.findById(id)).thenReturn(Optional.of(coachEntity));

        assertDoesNotThrow(() -> coachService.deleteCoach(id));
        verify(coachRepository, times(1)).delete(coachEntity);
    }

    @Test
    void deleteCoach_InvalidId_ThrowsException() {
        Integer id = 1;
        when(coachRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> coachService.deleteCoach(id));
    }
}