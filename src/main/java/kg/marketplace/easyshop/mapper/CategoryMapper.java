package kg.marketplace.easyshop.mapper;

import kg.marketplace.easyshop.dto.CategoryDTO;
import kg.marketplace.easyshop.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CategoryMapper extends BaseMapper<Category,CategoryDTO>{


    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);



    @Override
    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryDTO categoryDTO);
}
