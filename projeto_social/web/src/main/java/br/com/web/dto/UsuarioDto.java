package br.com.web.dto;

import br.com.dominio.entidade.objetos.*;

public record UsuarioDto(String nome,
                         String sobrenome,
                         Cpf cpf,
                         Email email,
                         Telefone telefone,
                         Endereco endereco){
}
