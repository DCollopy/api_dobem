package br.com.web.controller;

import br.com.adaptador.service.EventoService;
import br.com.dominio.entidade.Evento;
import br.com.dominio.entidade.Usuario;
import br.com.dominio.usercase.EventoValida;
import br.com.web.converter.EventoMapper;
import br.com.web.converter.UsuarioMapper;
import br.com.web.dto.EventoDto;
import br.com.web.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {
    @Autowired
    private final EventoService eventoService;
    @Autowired
    private final EventoValida eventoValida;
    @Autowired
    private final EventoMapper eventoMapper;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    public EventoController(EventoService eventoService, EventoValida eventoValida, EventoMapper eventoMapper) {
        this.eventoService = eventoService;
        this.eventoValida = eventoValida;
        this.eventoMapper = eventoMapper;
    }

    @PostMapping
    public ResponseEntity<String> criarEvento(@RequestBody EventoDto eventoDto) {
        try {
            Evento evento = eventoMapper.toEntity(eventoDto);
            eventoValida.criaEvento(evento);
            String id = eventoService.salvarEvento(evento);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (IllegalArgumentException | InterruptedException | ExecutionException e) {
            Logger.getLogger("EVENTO").info("Erro ao criar evento: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDto> obterEvento(@PathVariable String id) {
        try {
            Evento evento = eventoService.obterEvento(id);
            if (evento != null) {
                EventoDto eventoDto = eventoMapper.toDto(evento);
                return new ResponseEntity<>(eventoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (InterruptedException | ExecutionException e) {
            Logger.getLogger("EVENTO").info("Erro ao obter evento: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<EventoDto>> listarEventos() {
        try {
            List<Evento> eventos = eventoService.listarEventos();
            List<EventoDto> eventoDtos = eventos.stream()
                    .map(eventoMapper::toDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(eventoDtos, HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            Logger.getLogger("EVENTO").info("Erro ao listar eventos: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEvento(@PathVariable String id, @RequestBody EventoDto eventoDto) {
        try {
            Evento evento = eventoMapper.toEntity(eventoDto);
            eventoValida.criaEvento(evento);
            boolean atualizado = eventoService.atualizarEvento(id, evento);
            return atualizado ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException | InterruptedException | ExecutionException e) {
            Logger.getLogger("EVENTO").info("Erro ao atualizar evento: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable String id) {
        try {
            boolean deletado = eventoService.deletarEvento(id);
            return deletado ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InterruptedException | ExecutionException e) {
            Logger.getLogger("EVENTO").info("Erro ao deletar evento: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/participantes")
    public ResponseEntity<Void> adicionarParticipante(@PathVariable String id, @RequestBody UsuarioDto participanteDto) {
        try {
            Evento evento = eventoService.obterEvento(id);
            if (evento == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Usuario participante = usuarioMapper.toEntity(participanteDto);
            eventoValida.criaParticipante(evento, participante);
            boolean atualizado = eventoService.adicionarParticipante(id, participante);
            return atualizado ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException | InterruptedException | ExecutionException e) {
            Logger.getLogger("EVENTO").info("Erro ao adicionar participante: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}