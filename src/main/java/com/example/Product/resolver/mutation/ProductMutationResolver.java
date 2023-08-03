package com.example.Product.resolver.mutation;

import com.example.Product.dto.productRequest.ProductRequest;
import com.example.Product.service.ProductCommandService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Service;

@Service
public class ProductMutationResolver implements GraphQLMutationResolver {

    private  final ProductCommandService productCommandService;

    public ProductMutationResolver(ProductCommandService productCommandService) {

        this.productCommandService = productCommandService;
    }

    public  String saveProduct(ProductRequest productRequest){
        productCommandService.saveProductDomain(productRequest);
        return "saved";
    }
}
