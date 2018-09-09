package com.ack.productconsumerapi.controller;

import com.ack.productconsumerapi.enums.EnjoymentEnum;
import com.ack.productconsumerapi.model.ProductDto;
import com.ack.productconsumerapi.service.ProductCreateService;
import com.ack.productconsumerapi.service.ProductUpdateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductCreateService productCreateService;

    @Mock
    private ProductUpdateService productUpdateService;

    @Test
    public void it_should_create_product(){
        //Given
        ProductDto productDto = ProductDto.builder()
                .name("name")
                .detail("detail")
                .build();

        //When
        productController.productDetail("advertiserId", productDto);
        //Then
        verify(productCreateService).create("advertiserId", productDto);
    }


    @Test
    public void it_should_rate_product() throws Exception{
        //Given

        //When
        productController.rateProduct("advertiserId", "1");
        //Then
        verify(productUpdateService).rateProduct("advertiserId", "1");
    }


    @Test
    public void it_should_enjoy_product(){
        //Given

        //When
        productController.enjoyProduct("advertiserId", EnjoymentEnum.NEGATIVE);
        //Then
        verify(productUpdateService).changeLikeCount("advertiserId", EnjoymentEnum.NEGATIVE);
    }


    @Test
    public void it_should_watch_product(){
        //Given

        //When
        productController.watchProduct("advertiserId");
        //Then
        verify(productUpdateService).watchProduct("advertiserId");
    }

}