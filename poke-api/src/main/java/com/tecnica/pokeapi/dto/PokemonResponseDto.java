package com.tecnica.pokeapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record PokemonResponseDto (
        String name,
        int height,
        int weight,
        @JsonProperty("abilities") List<AbilityDto> abilities,
        @JsonProperty("species") SpeciesDto species,
        @JsonProperty("forms") List<FormDto> forms
) {
    public record AbilityDto(AbilityInfo ability) {
        public record AbilityInfo(String name) {
        }
    }

    public record SpeciesDto(String name) {
    }

    public record FormDto(String name) {
    }
}