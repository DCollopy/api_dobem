package br.com.web.converter;

import br.com.dominio.entidade.Evento;
import br.com.web.dto.EventoDto;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    public Evento toEntity(EventoDto eventoDto) {
        return new Evento(
                eventoDto.nome_evento(),
                eventoDto.dia_evento(),
                eventoDto.descricao(),
                eventoDto.usuario()
        );
    }

    public EventoDto toDto(Evento evento) {
        return new EventoDto(
                evento.getNome_evento(),
                evento.getDia_evento(),
                evento.getDescricao(),
                evento.getUsuario()
        );
    }
}