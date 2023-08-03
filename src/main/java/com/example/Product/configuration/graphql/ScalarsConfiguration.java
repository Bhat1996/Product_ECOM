package com.example.Product.configuration.graphql;

import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScalarsConfiguration {

//    @Bean
//    public GraphQLScalarType longScalars() {
//        return ExtendedScalars.GraphQLLong;
//    }
//
//    @Bean
//    public GraphQLScalarType uploadScalars() {
//        return ApolloScalars.Upload;
//    }

    @Bean
    public GraphQLScalarType objectScalars() {
        return ExtendedScalars.Object;
    }
}
