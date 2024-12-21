package com.tecnica.pokeapi.dto;

import lombok.Builder;

@Builder
public record PokemonDto(
        Integer id,
        String name
) {}