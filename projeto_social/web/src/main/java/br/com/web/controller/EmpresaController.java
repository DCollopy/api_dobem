package br.com.web.controller;

import br.com.adaptador.service.EmpresaService;
import br.com.dominio.entidade.Empresa;
import br.com.web.converter.EmpresaMapper;
import br.com.web.dto.EmpresaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projeto/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    private final EmpresaMapper empresaMapper;

    public EmpresaController(EmpresaService empresaService, EmpresaMapper empresaMapper) {
        this.empresaService = empresaService;
        this.empresaMapper = empresaMapper;
    }

    @PostMapping
    public ResponseEntity<String> criarEmpresa(@RequestBody EmpresaDto empresaDto) throws ExecutionException, InterruptedException {
        String id = empresaService.salvarEmpresa(empresaMapper.toEntity(empresaDto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<EmpresaDto> obterEmpresa(@PathVariable String cnpj) throws ExecutionException, InterruptedException {
        Empresa empresa = empresaService.obterEmpresa(cnpj);
        if (empresa != null) {
            return new ResponseEntity<>(empresaMapper.toDto(empresa), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDto>> listarEmpresas() throws ExecutionException, InterruptedException {
        List<Empresa> empresas = empresaService.listarEmpresas();
        List<EmpresaDto> empresaDtos = empresas.stream()
                .map(empresaMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(empresaDtos, HttpStatus.OK);
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<Void> atualizarEmpresa(@PathVariable String cnpj, @RequestBody EmpresaDto empresaDto) throws ExecutionException, InterruptedException {
        Empresa atualizada = empresaService.atualizarEmpresa(cnpj, empresaMapper.toEntity(empresaDto));
        return atualizada != null ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable String cnpj) throws ExecutionException, InterruptedException {
        boolean deletada = empresaService.deletarEmpresa(cnpj);
        return deletada ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}