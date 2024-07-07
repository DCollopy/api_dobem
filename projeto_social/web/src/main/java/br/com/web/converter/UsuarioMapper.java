package br.com.web.converter;
import br.com.dominio.entidade.Usuario;
import br.com.web.dto.UsuarioDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    private final ModelMapper modelMapper;

    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Usuario toEntity(UsuarioDto usuarioDto) {
        return modelMapper.map(usuarioDto, Usuario.class);
    }

    public UsuarioDto toDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDto.class);
    }
}
