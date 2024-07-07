package br.com.dominio.entidade.objetos;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.logging.Logger;
@Data
@SuperBuilder
public class Endereco {
    private String rua;
    private String numero_endereco;
    private String bairro;
    private String cidade;
    private Cep cep;

    public Endereco() {}
    public Endereco(String rua, String numero_endereco, String bairro, String cidade, Cep cep) {
        if(rua == null || rua.trim().equals("")){
            Logger.getLogger("ENDEREÇO").info("Rua invalida");
            throw new IllegalArgumentException("Rua invalida");
        }
        if(numero_endereco == null || numero_endereco.trim().equals("")){
            Logger.getLogger("ENDEREÇO").info("Numero invalido");
            throw new IllegalArgumentException("Numero invalido");
        }
        if(bairro == null || bairro.trim().equals("")){
            Logger.getLogger("ENDEREÇO").info("Bairro invalido");
            throw new IllegalArgumentException("Bairro invalido");
        }
        if(cidade == null || cidade.trim().equals("")){
            Logger.getLogger("ENDEREÇO").info("Cidade invalida");
            throw new IllegalArgumentException("Cidade invalida");
        }

        this.rua = rua;
        this.numero_endereco = numero_endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }
}