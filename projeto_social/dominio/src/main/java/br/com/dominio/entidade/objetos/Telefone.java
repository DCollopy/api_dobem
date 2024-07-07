package br.com.dominio.entidade.objetos;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.logging.Logger;

@Data
@SuperBuilder
public class Telefone {

    private String ddd;

    private String numero_telefone;

    public Telefone(){}

    public Telefone(String ddd, String numero_telefone) {
        if(ddd == null || !ddd.matches("\\d{2}")) {
            Logger.getLogger("TELEFONE").info("DDD invalido");
            throw new IllegalArgumentException("DDD inválido");
        }
        if(numero_telefone == null || !numero_telefone.matches("^(\\d{5}-?\\d{4})$")) {
            Logger.getLogger("TELEFONE").info("Numero invalido");
            throw new IllegalArgumentException("Número de telefone inválido");
        }
        this.ddd = ddd;
        this.numero_telefone = numero_telefone;
    }
}
