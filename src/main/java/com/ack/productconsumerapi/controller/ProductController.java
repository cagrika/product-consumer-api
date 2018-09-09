package com.ack.productconsumerapi.controller;

import com.ack.productconsumerapi.enums.EnjoymentEnum;
import com.ack.productconsumerapi.model.ProductDto;
import com.ack.productconsumerapi.service.ProductCreateService;
import com.ack.productconsumerapi.service.ProductUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    private final ProductCreateService productCreateService;
    private final ProductUpdateService productUpdateService;

    @PostMapping(path = "/detail/{advertiserId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto productDetail(@PathVariable("advertiserId") final String advertiserId,
                              @RequestBody final ProductDto productDto){
        return productCreateService.create(advertiserId, productDto);
    }

    @PutMapping(path = "/enjoy/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void enjoyProduct(@PathVariable("id") final String id, @RequestParam("like") final EnjoymentEnum enjoymentEnum) {
        productUpdateService.changeLikeCount(id, enjoymentEnum);
    }

    @PutMapping(path = "/watch/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void watchProduct(@PathVariable("id") final String id) {
        productUpdateService.watchProduct(id);
    }

    @PutMapping(path = "/rate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void rateProduct(@PathVariable("id") final String id, @RequestParam("point") final String rate) throws Exception {
        productUpdateService.rateProduct(id, rate);
    }
}
