package br.com.dominio.usercase;

import br.com.dominio.entidade.Empresa;

import java.util.logging.Logger;

public class EmpresaValida {

    public Empresa criaEmpresa(Empresa empresa) {
        try {
            Empresa empresaCria = new Empresa(empresa.getRazaosocial(), empresa.getNomefantasia(), empresa.getCnpj());
            Logger.getLogger("EMPRESA").info("Empresa criada com sucesso");
            return empresaCria;
        } catch (Exception e) {
            Logger.getLogger("EMPRESA").info("Empresa nao criada");
            throw new IllegalArgumentException("Empresa nao criada");
        }
    }
}