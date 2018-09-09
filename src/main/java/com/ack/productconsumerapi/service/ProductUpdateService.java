package com.ack.productconsumerapi.service;

import com.ack.productconsumerapi.enums.EnjoymentEnum;
import com.ack.productconsumerapi.advice.ProductConsumerException;
import com.ack.productconsumerapi.repository.ProductRepository;
import com.ack.productconsumerapi.repository.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductUpdateService {

    private final ProductRepository productRepository;

    @Transactional
    public void changeLikeCount(String id, EnjoymentEnum enjoymentEnum) {
        ProductEntity productEntity = getProductEntity(id);
        if (enjoymentEnum.equals(EnjoymentEnum.NEGATIVE)) {
            log.info("Increasing dislike count for productId : {}", id);
            productEntity.setDisLikeCount(productEntity.getDisLikeCount() + 1);
            productRepository.save(productEntity);
            log.info("Increased dislike count for productId : {}", id);
        } else {
            log.info("Increasing like count for productId : {}", id);
            productEntity.setLikeCount(productEntity.getLikeCount() + 1);
            productRepository.save(productEntity);
            log.info("Increased dislike count for productId : {}", id);
        }
    }

    @Transactional
    public void watchProduct(String id) {
        ProductEntity productEntity = getProductEntity(id);
        log.info("Increasing watch count for productId : {}", id);
        productEntity.setWatchCount(productEntity.getWatchCount() + 1);
        productRepository.save(productEntity);
        log.info("Increased watch count for productId : {}", id);
    }

    @Transactional
    public void rateProduct(String id, String rate) throws Exception {
        validateRate(rate);
        ProductEntity productEntity = getProductEntity(id);
        log.info("New rate is being given for productId : {}, rate : {}", id, rate);
        Map<String, Long> rates = productEntity.getRates();
        if (rates.get(rate) != null) {
            rates.put(rate, rates.get(rate) + 1);
        } else {
            rates.put(rate, 1L);
        }
        productEntity.setRates(rates);
        productRepository.save(productEntity);
        log.info("New rate is saved for productId : {}, rate : {}", id, rate);

    }

    private void validateRate(String rate) {
        if (!Arrays.asList("1", "2", "3", "4", "5").contains(rate)) {
            throw new ProductConsumerException("Rate is not valid. Allowed rates are : '1','2','3','4','5'", -101);
        }
    }

    private ProductEntity getProductEntity(String id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (!productEntity.isPresent()) {
            throw new ProductConsumerException("Product not found for id : " + id, -99);
        }
        return productEntity.get();
    }
}
