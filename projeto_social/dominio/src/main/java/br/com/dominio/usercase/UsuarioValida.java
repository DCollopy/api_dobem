package br.com.dominio.usercase;

import br.com.dominio.entidade.Usuario;

import java.util.logging.Logger;

public class UsuarioValida {
    public Usuario criaUsuario(Usuario usuario){
        try{
            Usuario usu = new Usuario(usuario.getNome(),usuario.getSobrenome(),usuario.getCpf());
            Logger.getLogger("USUARIO").info("Usuario criado com sucesso");
            return usu;
        }catch (Exception e){
            Logger.getLogger("USUARIO").info("Usuario invalido");
            throw new IllegalArgumentException("Usuario invalido");
        }
    }
}
