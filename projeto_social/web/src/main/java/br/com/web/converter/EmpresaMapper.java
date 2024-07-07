package br.com.web.converter;

import br.com.dominio.entidade.Empresa;
import br.com.web.dto.EmpresaDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {

    private final ModelMapper modelMapper;
    public EmpresaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EmpresaDto toDto(Empresa empresa) {
        return modelMapper.map(empresa, EmpresaDto.class);
    }

    public Empresa toEntity(EmpresaDto empresaDto) {
        return modelMapper.map(empresaDto, Empresa.class);
    }
}