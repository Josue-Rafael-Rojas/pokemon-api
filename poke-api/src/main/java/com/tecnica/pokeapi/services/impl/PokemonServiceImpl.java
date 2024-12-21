package com.tecnica.pokeapi.services.impl;

import com.tecnica.pokeapi.config.PokeApiProperties;
import com.tecnica.pokeapi.dto.AbilityDto;
import com.tecnica.pokeapi.dto.AbilityResponseDto;
import com.tecnica.pokeapi.dto.PokemonResponseDto;
import com.tecnica.pokeapi.exceptions.FetchException;
import com.tecnica.pokeapi.exceptions.ResourceNotFoundException;
import com.tecnica.pokeapi.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private static final String POKEMON_URL_TEMPLATE = "%s/pokemon/%s";
    private static final String ABILITY_URL_TEMPLATE = "%s/ability?limit=367";
    private static final int ABILITY_LIMIT = 50;

    private final PokeApiProperties pokeApiProperties;
    private final RestTemplate restTemplate;

    @Override
    public PokemonResponseDto getPokemonByIdentifier(String identifier) {
        String url = String.format(POKEMON_URL_TEMPLATE, pokeApiProperties.getBaseUrl(), identifier);
        try {
            return restTemplate.getForObject(url, PokemonResponseDto.class);
        } catch (RestClientException e) {
            throw new FetchException("Error fetching Pokemon data: " + e.getMessage());
        }
    }

    @Override
    public List<String> getAbilities() {
        String url = String.format(ABILITY_URL_TEMPLATE, pokeApiProperties.getBaseUrl());
        try {
            AbilityResponseDto response = restTemplate.getForObject(url, AbilityResponseDto.class);
            if (response != null && response.results() != null) {
                return response.results().stream()
                        .map(AbilityDto::name)
                        .sorted()
                        .limit(ABILITY_LIMIT)
                        .toList();
            } else {
                throw new ResourceNotFoundException("No abilities found in the response");
            }
        } catch (RestClientException e) {
            throw new FetchException("Error fetching abilities: " + e.getMessage());
        }
    }
}