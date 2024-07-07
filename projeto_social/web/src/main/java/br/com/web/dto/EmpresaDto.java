package br.com.web.dto;

import br.com.dominio.entidade.objetos.Cnpj;

public record EmpresaDto(String razaosocial, String nomefantasia, Cnpj cnpj) {
}
