package br.com.web.dto;

import br.com.dominio.entidade.Usuario;

public record EventoDto(String nome_evento, String dia_evento, String descricao, Usuario usuario) {
}
