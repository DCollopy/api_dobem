package br.com.dominio.entidade.objetos;

import lombok.Data;

import java.util.logging.Logger;
@Data
public class Email {
    private String endereco_email;
    public Email(){}
    public Email(String endereco_email) {

        if(!endereco_email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            Logger.getLogger("EMAIL").info("Email invalido");
            throw new IllegalArgumentException("Email inválido");
        }
        this.endereco_email = endereco_email;
    }
}
