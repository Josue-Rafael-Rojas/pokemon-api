package com.tecnica.pokeapi.mappers;

import com.tecnica.pokeapi.dto.RequestCoachDto;
import com.tecnica.pokeapi.dto.ResponseCoachDto;
import com.tecnica.pokeapi.model.CoachEntity;

public class CoachMapper {

    public static ResponseCoachDto toDto(CoachEntity coach) {
        return new ResponseCoachDto(
                coach.getId(),
                coach.getName(),
                coach.getCity(),
                coach.getScore(),
                coach.getFavoritePokemons()
        );
    }

    public static CoachEntity toEntity(RequestCoachDto dto) {
        return CoachEntity.builder()
                .name(dto.name())
                .favoritePokemons(dto.favoritePokemons())
                .city(dto.city())
                .score(dto.score())
                .build();
    }
}