package br.com.dominio.entidade;

import br.com.dominio.entidade.objetos.Cnpj;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Empresa {

    private String razaosocial;

    private String nomefantasia;

    private Cnpj cnpj;

    private Funcionario funcionario;

    public Empresa() {
    }

    public Empresa(String razaosocial, String nomefantasia, Cnpj cnpj) {
        this.razaosocial = razaosocial;
        this.nomefantasia = nomefantasia;
        this.cnpj = cnpj;
    }
}