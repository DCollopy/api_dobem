package br.com.dominio.entidade.objetos;

import lombok.Data;

import java.util.logging.Logger;

@Data
public class Cep {
    private String cep;
    public Cep() {}

    public Cep(String cep) {
        if (cep == null || !cep.matches("^(\\d{5}-\\d{3})$"))
        {
            Logger.getLogger("CEP").info("CEP invalido");
            throw new IllegalArgumentException("CEP invalido");
        }
        this.cep = cep;
    }
}
