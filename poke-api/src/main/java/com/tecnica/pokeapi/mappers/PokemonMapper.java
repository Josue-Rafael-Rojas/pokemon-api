package com.tecnica.pokeapi.mappers;

import com.tecnica.pokeapi.dto.PokemonDto;
import com.tecnica.pokeapi.model.PokemonEntity;
import org.springframework.stereotype.Component;

@Component
public class PokemonMapper {

    public static PokemonDto toDto(PokemonEntity pokemon) {
        PokemonDto pokemonDto = new PokemonDto(
                pokemon.getId(),
                pokemon.getName());
                return pokemonDto;
    }

    public static PokemonEntity toEntity(PokemonDto dto) {
        PokemonEntity pokemon = new PokemonEntity();
        pokemon.setId(dto.id());
        pokemon.setName(dto.name());

        return pokemon;
    }
}