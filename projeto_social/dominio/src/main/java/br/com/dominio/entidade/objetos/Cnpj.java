package br.com.dominio.entidade.objetos;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.logging.Logger;

@Data
@SuperBuilder
public class Cnpj {
    private String numero_cnpj;

    public Cnpj() {
    }

    public Cnpj(String numero_cnpj) {

        if (numero_cnpj == null || !numero_cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")) {
            Logger.getLogger("CNPJ").info("Cnpj invalido");
            throw new IllegalArgumentException("Número do CNPJ não pode ser nulo ou vazio");
        }

        this.numero_cnpj = numero_cnpj;
    }
}
