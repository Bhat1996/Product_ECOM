package com.example.Product.dto.productResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
public class ProductResponse {
    private List<String> productDomainIds;
    private List<String> productCategoryIds;
    private String productType;
    private List<String> companyIds;
    private String chemicals;
    private String macroComposition;
    private String microComposition;
    private Map<String,String> storage;
    private Map<String,String>  precautions;
}
