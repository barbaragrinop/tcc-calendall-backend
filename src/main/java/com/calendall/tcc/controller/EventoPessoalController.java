package com.calendall.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.calendall.tcc.model.EventoPessoal;
import com.calendall.tcc.model.dto.EventoPessoalDTO;
import com.calendall.tcc.service.EventoPessoalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/eventoPessoal")
@Tag(name = "Evento Pessoal", description = "Gerenciamento de eventos pessoais")
@RequiredArgsConstructor

public class EventoPessoalController {

    @Autowired
    private final EventoPessoalService eventoPessoalService;

    @PostMapping("/{id_evento}/criarEventoPessoal")
    public ResponseEntity<?> post(@PathVariable Long id_evento,
            @RequestBody @Valid EventoPessoalDTO eventoPessoalDTO) {

        try {
            EventoPessoal eventoPessoal = eventoPessoalService.CriarEventoPessoal(id_evento, eventoPessoalDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(eventoPessoal);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());

        }
    }
}