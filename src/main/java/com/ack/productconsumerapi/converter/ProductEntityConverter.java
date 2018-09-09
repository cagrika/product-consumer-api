package com.ack.productconsumerapi.converter;

import com.ack.productconsumerapi.model.ProductDto;
import com.ack.productconsumerapi.repository.model.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityConverter implements Converter<ProductDto, ProductEntity> {

    @Override
    public ProductEntity convert(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setDescription(productDto.getDetail());
        productEntity.setName(productDto.getName());
        productEntity.setAdvertiserId(productDto.getAdvertiserId());
        return productEntity;
    }
}
