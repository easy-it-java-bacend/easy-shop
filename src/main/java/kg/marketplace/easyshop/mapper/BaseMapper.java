package kg.marketplace.easyshop.mapper;

import java.util.List;

public interface BaseMapper<ENTITY, DTO> {

    ENTITY toEntity(DTO dto);
    DTO toDTO(ENTITY entity);
    List<ENTITY> toEntityList(List<DTO> dtoList);
    List<DTO> toDTOList(List<ENTITY> entityList);


}
