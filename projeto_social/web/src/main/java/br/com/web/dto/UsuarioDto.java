package br.com.web.dto;

import br.com.dominio.entidade.objetos.*;
import lombok.Data;

@Data
public class UsuarioDto{
    private String cpf;
    private String nome;

    private String sobrenome;

    private Email email;

    private Telefone telefone;

    private Endereco endereco;
}
