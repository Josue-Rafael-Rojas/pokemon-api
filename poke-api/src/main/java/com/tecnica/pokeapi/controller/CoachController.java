package com.tecnica.pokeapi.controller;

import com.tecnica.pokeapi.dto.RequestCoachDto;
import com.tecnica.pokeapi.dto.ResponseCoachDto;
import com.tecnica.pokeapi.services.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/coaches")
@RequiredArgsConstructor
public class CoachController {

    private final CoachService coachService;

    @PostMapping
    public ResponseEntity<ResponseCoachDto> createTrainer(@RequestBody RequestCoachDto requestCoachDto) {
        ResponseCoachDto createdCoach = coachService.createCoach(requestCoachDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoach);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCoachDto> updateCoach(@PathVariable Integer id, @RequestBody RequestCoachDto requestCoachDto) {
        var updatedCoach = coachService.updateCoach(id, requestCoachDto);
        return ResponseEntity.ok(updatedCoach);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCoachDto> getCoach(@PathVariable Integer id) {
        ResponseCoachDto coach = coachService.getCoach(id);
        return ResponseEntity.ok(coach);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Integer id) {
        coachService.deleteCoach(id);
        return ResponseEntity.noContent().build();
    }
}