package com.example.Product.mapper;

import com.example.Product.domain.ProductDomains;
import com.example.Product.dto.productRequest.ProductRequest;
import com.example.Product.dto.productResponse.ProductResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;


@Mapper(componentModel = "spring")
@Service
public interface ProductRequestResponseMapper {

    ProductDomains getProductForSaving(ProductRequest productRequest);

    ProductResponse getProductResponse(ProductDomains productDomains);

}
