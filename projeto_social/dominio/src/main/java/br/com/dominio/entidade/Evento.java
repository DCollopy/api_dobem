package br.com.dominio.entidade;

import lombok.Data;

import java.util.List;
import java.util.logging.Logger;

@Data
public class Evento {
    private String nome_evento;
    private String dia_evento;
    private String descricao;
    private Usuario usuario;
    private List<Usuario> participantes;

    public Evento(){}
    public Evento(String nome_evento, String dia_evento, String descricao,Usuario usuario) {
        if(nome_evento == null || nome_evento.trim().equals("")){
            Logger.getLogger("Evento").info("Nome invalido");
            throw new IllegalArgumentException("Nome invalido");
        }
        if(dia_evento == null || dia_evento.trim().equals("")){
            Logger.getLogger("Evento").info("Dia invalido");
            throw new IllegalArgumentException("Dia invalido");
        }
        if(descricao == null || descricao.trim().equals("")){
            Logger.getLogger("Evento").info("Descricao invalido");
            throw new IllegalArgumentException("Descricao invalido");
        }
        this.nome_evento = nome_evento;
        this.dia_evento = dia_evento;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public Evento(List<Usuario> participantes){
        this.participantes = participantes;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

    public void adicionarParticipante(Usuario participante) {
        this.participantes.add(participante);
    }

    public void removerParticipante(Usuario participante) {
        this.participantes.remove(participante);
    }
}
