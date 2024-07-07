package br.com.dominio.entidade.objetos;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.logging.Logger;
@Data
@SuperBuilder
public class Cpf {

    private String numero_cpf;

    public Cpf(){}
    public Cpf(String numero_cpf) {
        if (numero_cpf == null || !numero_cpf.matches("^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$")) {
            Logger.getLogger("CPF").info("CPF invalido");
            throw new IllegalArgumentException("CPF inválido");
        }

        this.numero_cpf = numero_cpf;
    }
}
