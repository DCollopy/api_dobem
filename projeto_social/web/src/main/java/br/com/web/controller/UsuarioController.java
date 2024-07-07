package br.com.web.controller;

import br.com.adaptador.service.UsuarioService;
import br.com.dominio.entidade.Usuario;
import br.com.web.converter.UsuarioMapper;
import br.com.web.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projeto/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    private UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDto usuarioDto) throws ExecutionException, InterruptedException {
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        // Validar ou processar o usuário conforme necessário antes de salvar
        String cpf = usuarioService.salvarUsuario(usuario);
        return new ResponseEntity<>(cpf, HttpStatus.CREATED);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UsuarioDto> obterUsuario(@PathVariable String cpf) throws ExecutionException, InterruptedException {
        Usuario usuario = usuarioService.obterUsuario(cpf);
        if (usuario != null) {
            UsuarioDto usuarioDto = usuarioMapper.toDto(usuario);
            return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() throws ExecutionException, InterruptedException {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        List<UsuarioDto> usuariosDto = usuarios.stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(usuariosDto, HttpStatus.OK);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable String cpf, @RequestBody UsuarioDto usuarioDto) throws ExecutionException, InterruptedException {
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        // Validar ou processar o usuário conforme necessário antes de atualizar
        Usuario atualizado = usuarioService.atualizarUsuario(cpf, usuario);
        if (atualizado != null) {
            UsuarioDto atualizadoDto = usuarioMapper.toDto(atualizado);
            return new ResponseEntity<>(atualizadoDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String cpf) throws ExecutionException, InterruptedException {
        boolean deletado = usuarioService.deletarUsuario(cpf);
        return deletado ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}