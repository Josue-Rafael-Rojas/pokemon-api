package com.tecnica.pokeapi.services;

import com.tecnica.pokeapi.dto.PokemonResponseDto;

import java.util.List;

public interface PokemonService {
    PokemonResponseDto getPokemonByIdentifier(String identifier);
    List<String> getAbilities();
}
