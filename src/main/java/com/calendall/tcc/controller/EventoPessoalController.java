package com.calendall.tcc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.calendall.tcc.model.EventoPessoal;
import com.calendall.tcc.model.Usuario;
import com.calendall.tcc.model.dto.EventoPessoalDTO;
import com.calendall.tcc.model.dto.EventoPessoalEdicaoDTO;
import com.calendall.tcc.model.dto.EventoPessoalNovoDTO;
import com.calendall.tcc.repository.UsuarioRepository;
import com.calendall.tcc.service.EventoPessoalService;

import io.swagger.v3.oas.annotations.Operation;
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

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/{id_evento}/criarEventoPessoal")
    @Operation(summary = "Realiza criação de um evento pessoal a partir de um evento já existente", description = "Realiza criação de um evento pessoal a partir de um evento já existente")
    public ResponseEntity<?> post(@PathVariable Long id_evento,
            @RequestBody @Valid EventoPessoalDTO eventoPessoalDTO) {

        try {
            EventoPessoal eventoPessoal = eventoPessoalService.CriarEventoPessoal(id_evento, eventoPessoalDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(eventoPessoal);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());

        }
    }

    @PostMapping("/criarEventoPessoal")
    @Operation(summary = "Realiza criação de um evento pessoal", description = "Realiza criação de um evento pessoal")
    public ResponseEntity<?> post(@RequestBody @Valid EventoPessoalNovoDTO eventoPessoalNovoDTO) {

        try {
            EventoPessoal eventoPessoal = eventoPessoalService.CriarEventoPessoalIndividual(eventoPessoalNovoDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(eventoPessoal);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());

        }
    }

    @GetMapping("/{id_usuario}/buscarEventosPessoaisPorUsuario")
    @Operation(summary = "Busca eventos pessoais por usuário", description = "Busca todos os eventos pessoais associados ao usuário.")
    public ResponseEntity<?> getEventosPessoaisPorUsuario(@PathVariable Long id_usuario) {

        try {

            Optional<Usuario> usuarioProcurado = usuarioRepository.findById(id_usuario);

            if (usuarioProcurado.isEmpty()) {
                throw new Exception("Usuário não encontrado");
            }

            List<EventoPessoal> eventosPessoais = eventoPessoalService
                    .BuscarEventosPessoaisPorUsuario(usuarioProcurado);
            return ResponseEntity.ok(eventosPessoais);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id_evento_pessoal}/deletarEventoPessoal")
    @Operation(summary = "Deleta um evento pessoal", description = "Deleta um evento pessoal por ID")
    public ResponseEntity<?> deleteEventoPessoal(@PathVariable Long id_evento_pessoal) {

        try {
            eventoPessoalService.DeletarEventoPessoal(id_evento_pessoal);
            return ResponseEntity.ok("Evento pessoal deletado com sucesso.");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{id_usuario}/buscarEventosPessoaisNaDataAtualPorUsuario")
    @Operation(summary = "Busca eventos pessoais por usuário na data atual", description = "Busca todos os eventos pessoais do usuário com base na data atual.")
    public ResponseEntity<?> getEventosPessoaisDataAtualPorUsuario(@PathVariable Long id_usuario) {

        try {

            Optional<Usuario> usuarioProcurado = usuarioRepository.findById(id_usuario);

            if (usuarioProcurado.isEmpty()) {
                throw new Exception("Usuário não encontrado");
            }

            List<EventoPessoal> eventosPessoais = eventoPessoalService
                    .BuscarEventosPessoaisNaDataAtual(id_usuario);
            return ResponseEntity.ok(eventosPessoais);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{id_usuario}/buscarEventosPessoaisAposDataAtualPorUsuario")
    @Operation(summary = "Busca eventos pessoais por usuário após data atual", description = "Busca todos os eventos pessoais do usuário nos próximos dias com base na data atual.")
    public ResponseEntity<?> getEventosPessoaisAposDataAtualPorUsuario(@PathVariable Long id_usuario) {

        try {

            Optional<Usuario> usuarioProcurado = usuarioRepository.findById(id_usuario);

            if (usuarioProcurado.isEmpty()) {
                throw new Exception("Usuário não encontrado");
            }

            List<EventoPessoal> eventosPessoais = eventoPessoalService
                    .BuscarEventosPessoaisAposDataAtual(id_usuario);
            return ResponseEntity.ok(eventosPessoais);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PatchMapping("/{id_evento_pessoal}/editarEventoPessoal")
    @Operation(summary = "Edita um evento pessoal", description = "Edita os dados de um evento pessoal específico")
    public ResponseEntity<?> editarEventoPessoalParcialmente(@PathVariable Long id_evento_pessoal,
            @RequestBody @Valid EventoPessoalEdicaoDTO eventoPessoalEdicaoDTO) {

        try {
            EventoPessoal eventoPessoalAtualizado = eventoPessoalService.AtualizarEventoPessoal(id_evento_pessoal,
            eventoPessoalEdicaoDTO);
            return ResponseEntity.ok(eventoPessoalAtualizado);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}