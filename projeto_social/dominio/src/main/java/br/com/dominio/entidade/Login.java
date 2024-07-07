package br.com.dominio.entidade;

import br.com.dominio.entidade.objetos.*;
import lombok.Data;

@Data
public class Login {

    private Email email;
    private String senha;

    public Login(Email email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Login(){}

    public Login(String enderecoEmail, String senha) {
    }
}

