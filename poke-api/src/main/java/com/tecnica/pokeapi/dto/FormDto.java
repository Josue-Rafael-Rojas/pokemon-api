package com.tecnica.pokeapi.dto;

import lombok.Builder;

@Builder
public record FormDto (
    Integer id,
    String formName
){
}
