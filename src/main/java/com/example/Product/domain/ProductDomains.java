package com.example.Product.domain;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.example.Product.metadata.VertexName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

@Document(VertexName.PRODUCT_DOMAIN)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDomains {
    @Id
    private String id;
    @ArangoId
    private  String arangoId;
    private List<String> productDomainIds;
    private List<String> productCategoryIds;
    private String productType;
    private List<String> companyIds;
    private String chemicals;
    private String macroComposition;
    private String microComposition;
    private Map<String,String> storage;
    private Map<String,String>  precautions;
//    private List<String> images;



}
