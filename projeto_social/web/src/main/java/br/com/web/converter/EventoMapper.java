package br.com.web.converter;

import br.com.dominio.entidade.Evento;
import br.com.web.dto.EventoDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    private final ModelMapper modelMapper;
    public EventoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Evento toEntity(EventoDto eventoDto) {
        return modelMapper.map(eventoDto,Evento.class);
    }

    public EventoDto toDto(Evento evento) {
        return modelMapper.map(evento,EventoDto.class);
    }
}