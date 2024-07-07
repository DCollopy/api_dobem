package br.com.dominio.usercase;

import br.com.dominio.entidade.Login;

import java.util.logging.Logger;

public class LoginValida {

    public Login criaLogin(Login login){
        try {
            Login log = new Login(login.getEmail().getEndereco_email(),login.getSenha());
            Logger.getLogger("Login").info("Usuario criado com sucesso");
            return log;
        }catch (Exception e){
            Logger.getLogger("Login").info("Usuario invalido");
            throw new IllegalArgumentException("Usuario invalido");
        }
    }
}
