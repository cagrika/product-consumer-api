package com.ack.productconsumerapi.service;

import com.ack.productconsumerapi.converter.ProductDtoConverter;
import com.ack.productconsumerapi.converter.ProductEntityConverter;
import com.ack.productconsumerapi.model.ProductDto;
import com.ack.productconsumerapi.repository.ProductRepository;
import com.ack.productconsumerapi.repository.model.ProductEntity;
import com.sun.org.apache.xpath.internal.Arg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductCreateServiceTest {

    @InjectMocks
    private ProductCreateService productCreateService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductEntityConverter productEntityConverter;

    @Mock
    private ProductDtoConverter productDtoConverter;


    @Test
    public void it_should_create_product() {
        //Given
        ProductDto productDto = ProductDto.builder()
                .detail("detail")
                .name("name")
            .build();
        ProductEntity productEntity = new ProductEntity();
        when(productEntityConverter.convert(productDto)).thenReturn(productEntity);
        when(productDtoConverter.convert(productEntity)).thenReturn(productDto);
        //When
        ProductDto result = productCreateService.create("advertiserId", productDto);
        //Then
        ArgumentCaptor<ProductEntity> productEntityArgumentCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(productEntityArgumentCaptor.capture());
        assertThat(productEntityArgumentCaptor.getValue().getAdvertiserId()).isEqualTo("advertiserId");
        assertThat(result).isEqualTo(productDto);
    }
}