package com.tecnica.pokeapi.dto;

import lombok.Builder;



@Builder
public record SimplePokemonDto(
        String name
) {}