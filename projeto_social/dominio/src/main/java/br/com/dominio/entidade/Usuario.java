package br.com.dominio.entidade;

import br.com.dominio.entidade.objetos.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;


import java.util.logging.Logger;

@Data
@SuperBuilder
public class Usuario {

    private String nome;

    private String sobrenome;

    private String cpf;

    public Usuario() {}
    public Usuario(String nome, String sobrenome, String cpf) {
        if(nome == null || nome.trim().equals("")){
            Logger.getLogger("USUARIO").info("Nome invalido");
            throw new IllegalArgumentException("Nome invalido");
        }
        if(sobrenome == null || sobrenome.trim().equals("")){
            Logger.getLogger("USUARIO").info("Sobrenome invalido");
            throw new IllegalArgumentException("Sobrenome invalido");
        }
        if(cpf == null){
            Logger.getLogger("USUARIO").info("CPF invalido");
            throw new IllegalArgumentException("CPF invalido");
        }
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }
    public String getCpf() {
        return cpf;
    }
}