package br.com.dominio.entidade;

import br.com.dominio.entidade.objetos.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Funcionario extends Usuario {

    private Empresa empresa;

    public Funcionario(){
        super();
    }

    public Funcionario(String nome, String sobrenome, Cpf cpf, Email email, Telefone telefone, Endereco endereco, Cnpj cnpj) {
    }
}
