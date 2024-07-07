package br.com.dominio.entidade;

import br.com.dominio.entidade.objetos.*;
import lombok.Data;


@Data
public class Funcionario extends Usuario {

    private Empresa empresa;

    public Funcionario(){
        super();
    }

    public Funcionario(String nome, String sobrenome, String cpf, Cnpj cnpj) {
    }
}
