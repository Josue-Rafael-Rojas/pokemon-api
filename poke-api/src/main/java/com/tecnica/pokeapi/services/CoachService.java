package com.tecnica.pokeapi.services;


import com.tecnica.pokeapi.dto.RequestCoachDto;
import com.tecnica.pokeapi.dto.ResponseCoachDto;

public interface CoachService {
    ResponseCoachDto createCoach(RequestCoachDto requestCoachDto);

    ResponseCoachDto updateCoach(Integer id, RequestCoachDto requestCoachDto);

    ResponseCoachDto getCoach(Integer id);

    void deleteCoach(Integer id);
}
