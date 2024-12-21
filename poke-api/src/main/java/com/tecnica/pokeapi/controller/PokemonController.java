package com.tecnica.pokeapi.controller;

import com.tecnica.pokeapi.dto.PokemonResponseDto;
import com.tecnica.pokeapi.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("{identifier}")
    public ResponseEntity<PokemonResponseDto> createPokemon(@PathVariable String identifier) {
        return ResponseEntity.ok(pokemonService.getPokemonByIdentifier(identifier));
    }

    @GetMapping("/abilities")
    public ResponseEntity<List<String>> getAbilities() {
        return ResponseEntity.ok(pokemonService.getAbilities());
    }

}
