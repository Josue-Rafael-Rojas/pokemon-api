package com.tecnica.pokeapi.services.impl;


import com.tecnica.pokeapi.config.PokeApiProperties;
import com.tecnica.pokeapi.dto.RequestCoachDto;
import com.tecnica.pokeapi.dto.ResponseCoachDto;
import com.tecnica.pokeapi.exceptions.ResourceNotFoundException;
import com.tecnica.pokeapi.mappers.CoachMapper;
import com.tecnica.pokeapi.model.CoachEntity;
import com.tecnica.pokeapi.repository.CoachRepository;
import com.tecnica.pokeapi.services.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final PokeApiProperties pokeApiProperties;
    private final RestTemplate restTemplate;

    @Override
    public ResponseCoachDto createCoach(RequestCoachDto requestCoachDto) {
        for (String pokemon : requestCoachDto.favoritePokemons()) {
            if (!isPokemonValid(pokemon)) {
                throw new ResourceNotFoundException("Pokemon " + pokemon + " does not exist in the PokeAPI.");
            }
        }
        var coach = CoachMapper.toEntity(requestCoachDto);
        coach = coachRepository.save(coach);
        return CoachMapper.toDto(coach);
    }

    private boolean isPokemonValid(String pokemonName) {
        String url = String.format("%s/pokemon/%s", pokeApiProperties.getBaseUrl(), pokemonName);
        try {
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (RestClientException e) {
            return false;
        }
    }

    @Override
    public ResponseCoachDto updateCoach(Integer id, RequestCoachDto requestCoachDto) {
        CoachEntity coach = findCoachById(id);

        if (requestCoachDto.name() != null) {
            coach.setName(requestCoachDto.name());
        }
        if (requestCoachDto.city() != null) {
            coach.setCity(requestCoachDto.city());
        }
        if (requestCoachDto.score() != null) {
            coach.setScore(requestCoachDto.score());
        }
        if (requestCoachDto.favoritePokemons() != null) {
            coach.setFavoritePokemons(requestCoachDto.favoritePokemons());
        }

        coach = coachRepository.save(coach);
        return CoachMapper.toDto(coach);
    }

    @Override
    public ResponseCoachDto getCoach(Integer id) {
        CoachEntity coach = findCoachById(id);
        return CoachMapper.toDto(coach);
    }

    @Override
    public void deleteCoach(Integer id) {
        CoachEntity coach = findCoachById(id);
        coachRepository.delete(coach);
    }

    private CoachEntity findCoachById(Integer id) {
        return coachRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coach with ID " + id + " not found."));
    }
}
