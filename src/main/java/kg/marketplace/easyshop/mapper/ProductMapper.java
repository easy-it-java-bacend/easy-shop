package kg.marketplace.easyshop.mapper;

import kg.marketplace.easyshop.dto.ProductDTO;
import kg.marketplace.easyshop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ProductMapper extends BaseMapper<Product, ProductDTO> {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Override
    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductDTO productDTO);

}
