package br.com.web.dto;

import br.com.dominio.entidade.Funcionario;
import br.com.dominio.entidade.objetos.Cnpj;
import lombok.Data;

@Data
public class EmpresaDto {
    private String razaosocial;

    private String nomefantasia;

    private Cnpj cnpj;

    private Funcionario funcionario;

}
