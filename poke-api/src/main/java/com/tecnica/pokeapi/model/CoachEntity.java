package com.tecnica.pokeapi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;


@Entity
@Table(name = "coach")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class CoachEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String city;
    private Float score;

    @ElementCollection
    @CollectionTable(name = "coach_favorite_pokemons", joinColumns = @JoinColumn(name = "coach_id"))
    @Column(name = "pokemon_id")
    private List<String> favoritePokemons;

}