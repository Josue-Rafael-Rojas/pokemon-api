package com.tecnica.pokeapi.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.tecnica.pokeapi.config.PokeApiProperties;
import com.tecnica.pokeapi.dto.AbilityDto;
import com.tecnica.pokeapi.dto.AbilityResponseDto;
import com.tecnica.pokeapi.dto.PokemonResponseDto;
import com.tecnica.pokeapi.exceptions.FetchException;
import com.tecnica.pokeapi.exceptions.ResourceNotFoundException;
import com.tecnica.pokeapi.services.impl.PokemonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

    @Mock
    private PokeApiProperties pokeApiProperties;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @BeforeEach
    void setUp() {
        when(pokeApiProperties.getBaseUrl()).thenReturn("https://pokeapi.co/api/v2");
    }

    @Test
    void getPokemonByIdentifier_ValidIdentifier_Success() {
        String identifier = "pikachu";
        PokemonResponseDto mockResponse = PokemonResponseDto.builder().build();
        when(restTemplate.getForObject(anyString(), eq(PokemonResponseDto.class))).thenReturn(mockResponse);

        PokemonResponseDto response = pokemonService.getPokemonByIdentifier(identifier);

        assertNotNull(response);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(PokemonResponseDto.class));
    }

    @Test
    void getPokemonByIdentifier_InvalidIdentifier_ThrowsFetchException() {
        String identifier = "invalidPokemon";
        when(restTemplate.getForObject(anyString(), eq(PokemonResponseDto.class))).thenThrow(new RestClientException("Not Found"));

        assertThrows(FetchException.class, () -> pokemonService.getPokemonByIdentifier(identifier));
    }

    @Test
    void getAbilities_Success() {
        AbilityResponseDto mockResponse = new AbilityResponseDto(List.of(new AbilityDto("overgrow"), new AbilityDto("blaze")));
        when(restTemplate.getForObject(anyString(), eq(AbilityResponseDto.class))).thenReturn(mockResponse);

        List<String> abilities = pokemonService.getAbilities();

        assertNotNull(abilities);
        assertEquals(2, abilities.size());
        assertTrue(abilities.contains("overgrow"));
        assertTrue(abilities.contains("blaze"));
        verify(restTemplate, times(1)).getForObject(anyString(), eq(AbilityResponseDto.class));
    }

    @Test
    void getAbilities_NoAbilitiesFound_ThrowsResourceNotFoundException() {
        AbilityResponseDto mockResponse = new AbilityResponseDto(null);
        when(restTemplate.getForObject(anyString(), eq(AbilityResponseDto.class))).thenReturn(mockResponse);

        assertThrows(ResourceNotFoundException.class, () -> pokemonService.getAbilities());
    }

    @Test
    void getAbilities_RestClientException_ThrowsFetchException() {
        when(restTemplate.getForObject(anyString(), eq(AbilityResponseDto.class))).thenThrow(new RestClientException("Error"));

        assertThrows(FetchException.class, () -> pokemonService.getAbilities());
    }
}