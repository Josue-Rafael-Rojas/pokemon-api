package com.tecnica.pokeapi.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ResponseCoachDto(
    Integer id,
    String name,
    String city,
    Float score,
    List<String> favoritePokemons
) { }
