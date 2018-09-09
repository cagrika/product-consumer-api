package com.ack.productconsumerapi.repository;

import com.ack.productconsumerapi.repository.model.ProductEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductRepository extends CrudRepository<ProductEntity, String> {


}
