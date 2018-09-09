package com.ack.productconsumerapi.service;

import com.ack.productconsumerapi.advice.ProductConsumerException;
import com.ack.productconsumerapi.enums.EnjoymentEnum;
import com.ack.productconsumerapi.repository.ProductRepository;
import com.ack.productconsumerapi.repository.model.ProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductUpdateServiceTest {

    @InjectMocks
    private ProductUpdateService productUpdateService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void it_should_rate_product() throws Exception {
        //Given
        ProductEntity productEntity = new ProductEntity();
        Map<String, Long> rates = new HashMap<>();
        rates.put("1", 4L);
        rates.put("2", 5L);
        productEntity.setRates(rates);
        when(productRepository.findById("productId")).thenReturn(Optional.of(productEntity));
        //When
        productUpdateService.rateProduct("productId", "2");
        //Then
        ArgumentCaptor<ProductEntity> productEntityArgumentCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(productEntityArgumentCaptor.capture());
        assertThat(productEntityArgumentCaptor.getValue().getRates().get("2")).isEqualTo(6);
    }


    @Test
    public void it_should_throw_exception_when_rate_is_not_valid()  {
        //Given

        //When
        Throwable thrown = catchThrowable(() -> productUpdateService.rateProduct("productId", "6"));
        //Then
        assertThat(thrown).isInstanceOf(ProductConsumerException.class);
        ProductConsumerException productConsumerException = (ProductConsumerException) thrown;
        assertThat(productConsumerException.getDetail()).isEqualTo("Rate is not valid. Allowed rates are : '1','2','3','4','5'");
    }

    @Test
    public void it_should_watch_product() {
        //Given
        ProductEntity productEntity = new ProductEntity();
        productEntity.setWatchCount(123);
        when(productRepository.findById("productId")).thenReturn(Optional.of(productEntity));
        //When
        productUpdateService.watchProduct("productId");
        //Then
        ArgumentCaptor<ProductEntity> productEntityArgumentCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(productEntityArgumentCaptor.capture());
        assertThat(productEntityArgumentCaptor.getValue().getWatchCount()).isEqualTo(124);
    }

    @Test
    public void it_should_dislike_product() {
        //Given
        ProductEntity productEntity = new ProductEntity();
        productEntity.setDisLikeCount(123);
        when(productRepository.findById("productId")).thenReturn(Optional.of(productEntity));
        //When
        productUpdateService.changeLikeCount("productId", EnjoymentEnum.NEGATIVE);
        //Then
        ArgumentCaptor<ProductEntity> productEntityArgumentCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(productEntityArgumentCaptor.capture());
        assertThat(productEntityArgumentCaptor.getValue().getDisLikeCount()).isEqualTo(124);
    }

    @Test
    public void it_should_like_product() {
        //Given
        ProductEntity productEntity = new ProductEntity();
        productEntity.setLikeCount(123);
        when(productRepository.findById("productId")).thenReturn(Optional.of(productEntity));
        //When
        productUpdateService.changeLikeCount("productId", EnjoymentEnum.POSITIVE);
        //Then
        ArgumentCaptor<ProductEntity> productEntityArgumentCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(productEntityArgumentCaptor.capture());
        assertThat(productEntityArgumentCaptor.getValue().getLikeCount()).isEqualTo(124);
    }
}