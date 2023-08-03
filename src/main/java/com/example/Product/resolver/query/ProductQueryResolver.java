package com.example.Product.resolver.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Service;

@Service
public class ProductQueryResolver implements GraphQLQueryResolver {

    public  String getAll(String id){
        return "";
    }

}
