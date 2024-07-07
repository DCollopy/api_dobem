package br.com.web.dto;

import br.com.dominio.entidade.Usuario;
import lombok.Data;

@Data
public class EventoDto {
    private String nome_evento;
    private String dia_evento;
    private String descricao;
    private Usuario usuario;
}
