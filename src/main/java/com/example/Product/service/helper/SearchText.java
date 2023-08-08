package com.example.Product.service.helper;

import com.example.Product.domain.ProductDomains;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SearchText {

    private final SavingCache savingCache;

    public SearchText(SavingCache savingCache) {
        this.savingCache = savingCache;
    }

    public void createSearchText(ProductDomains productDomains){

        Set<String> enData = new HashSet<>();
        Set<String> hnData = new HashSet<>();
        Set<String> pbData = new HashSet<>();

        addChemicals(productDomains,enData,hnData,pbData);
        addProductType(productDomains,enData,hnData,pbData);
    }

    private void addProductType(ProductDomains productDomains, Set<String> enData, Set<String> hnData, Set<String> pbData) {

        String productType = productDomains.getProductType();
        addToList(productType,enData,hnData,pbData);
    }

    private void addChemicals(ProductDomains productDomains, Set<String> enData, Set<String> hnData, Set<String> pbData) {

        String chemicals = productDomains.getChemicals();
        addToList(chemicals,enData,hnData,pbData);

    }

    private void addToList(String text, Set<String> enData, Set<String> hnData, Set<String> pbData) {

        boolean engData = StringUtils.isNotBlank(text);
        if (engData){
            String searchText = savingCache.searchPatternForSearchText(text);
            enData.add(searchText);
        }
    }

}
