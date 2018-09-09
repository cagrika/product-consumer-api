package com.ack.productconsumerapi.converter;

import com.ack.productconsumerapi.model.ProductDto;
import com.ack.productconsumerapi.repository.model.ProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductEntityConverterTest {

    @InjectMocks
    private ProductEntityConverter productEntityConverter;

    @Test
    public void it_should_convert_product_dto_to_entity() {
        //Given
        ProductDto productDto = ProductDto.builder()
                .name("name")
                .detail("detail")
                .advertiserId("advertiserId")
                .build();
        //When
        ProductEntity productEntity = productEntityConverter.convert(productDto);
        //Then
        assertThat(productEntity.getDescription()).isEqualTo("detail");
        assertThat(productEntity.getName()).isEqualTo("name");
        assertThat(productEntity.getAdvertiserId()).isEqualTo("advertiserId");
    }
}