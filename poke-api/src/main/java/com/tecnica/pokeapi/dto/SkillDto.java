package com.tecnica.pokeapi.dto;


import lombok.Builder;

import java.util.List;

@Builder
public record SkillDto (
        Integer id,
        String name
){
}
