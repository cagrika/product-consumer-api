package com.ack.productconsumerapi.service;

import com.ack.productconsumerapi.converter.ProductDtoConverter;
import com.ack.productconsumerapi.converter.ProductEntityConverter;
import com.ack.productconsumerapi.model.ProductDto;
import com.ack.productconsumerapi.repository.ProductRepository;
import com.ack.productconsumerapi.repository.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCreateService {

    private final ProductRepository productRepository;
    private final ProductEntityConverter productEntityConverter;
    private final ProductDtoConverter productDtoConverter;

    @Transactional
    public ProductDto create(String advertiserId, ProductDto productDto) {
        log.info("Creating product {} for advertiserId : {}", productDto, advertiserId);
        ProductEntity productEntity = productEntityConverter.convert(productDto);
        assert productEntity != null;
        productEntity.setId(UUID.randomUUID().toString());
        productEntity.setAdvertiserId(advertiserId);
        productRepository.save(productEntity);
        log.info("Created productId : {} for advertiserId : {}", productEntity.getId(), advertiserId);
        return productDtoConverter.convert(productEntity);
    }

}
