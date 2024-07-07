package br.com.dominio.usercase;

import br.com.dominio.entidade.Evento;
import br.com.dominio.entidade.Usuario;

import java.util.logging.Logger;

public abstract  class EventoValida {
    public Evento criaEvento(Evento evento){
        try{
            Evento eve = new Evento(evento.getNome_evento(),evento.getDia_evento(),evento.getDescricao(), evento.getUsuario());
            return eve;
        } catch (Exception e) {
            throw new IllegalArgumentException("Evento invalido");
        }
    }
    public void criaParticipante(Evento evento, Usuario participante) {
        try {
            if (participante == null) {
                throw new IllegalArgumentException("Participante não pode ser nulo");
            }
            evento.adicionarParticipante(participante);
            Logger.getLogger("EVENTO").info("Participante adicionado com sucesso ao evento");
        } catch (Exception e) {
            Logger.getLogger("EVENTO").info("Erro ao adicionar participante ao evento: " + e.getMessage());
            throw new IllegalArgumentException("Erro ao adicionar participante ao evento: " + e.getMessage());
        }
    }
}
