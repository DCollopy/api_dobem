package br.com.dominio.usercase;

import br.com.dominio.entidade.Funcionario;

import java.util.logging.Logger;

public abstract class FuncionarioValida {

    public Funcionario criaFuncionario(Funcionario funcionario){
        try{
            Funcionario func = new Funcionario(funcionario.getNome(), funcionario.getSobrenome()
                    , funcionario.getCpf(), funcionario.getEmail()
                    , funcionario.getTelefone(), funcionario.getEndereco(), funcionario.getEmpresa().getCnpj());
            Logger.getLogger("FUNCIONARIO").info("Funcionario criado com sucesso");
            return func;
        }catch (Exception e){
            Logger.getLogger("FUNCIONARIO").info("Funcionario invalido");
            throw new IllegalArgumentException("Funcionario invalido");
        }
    }
}
