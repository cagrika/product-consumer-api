package com.ack.productconsumerapi.converter;

import com.ack.productconsumerapi.model.ProductDto;
import com.ack.productconsumerapi.repository.model.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoConverter implements Converter<ProductEntity, ProductDto> {

    @Override
    public ProductDto convert(ProductEntity productEntity) {
        return ProductDto.builder()
                .detail(productEntity.getDescription())
                .name(productEntity.getName())
                .id(productEntity.getId())
                .advertiserId(productEntity.getAdvertiserId())
                .createdDate(productEntity.getCreatedDate())
                .build();
    }
}
