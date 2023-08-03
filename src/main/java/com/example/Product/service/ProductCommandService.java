package com.example.Product.service;

import com.example.Product.Repository.ProductRepository;
import com.example.Product.Repository.edge.CategoryRepository;
import com.example.Product.Repository.edge.CompanyRepository;
import com.example.Product.Repository.edge.ProductDomainRepository;
import com.example.Product.domain.ProductDomains;
import com.example.Product.domain.edge.ProductHasCategory;
import com.example.Product.domain.edge.ProductHasCompany;
import com.example.Product.domain.edge.ProductHasDomain;
import com.example.Product.dto.productRequest.ProductRequest;
import com.example.Product.dto.productResponse.ProductResponse;
import com.example.Product.mapper.ProductRequestResponseMapper;
import com.example.Product.metadata.EdgeName;
import com.example.Product.metadata.VertexName;
import com.example.Product.service.transaction.Action;
import com.example.Product.service.transaction.TransactionalWrapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.example.Product.metadata.EdgeName.PRODUCT_HAS_CATEGORY;
import static com.example.Product.metadata.EdgeName.PRODUCT_HAS_DOMAIN;

@Service
public class ProductCommandService {

    private final ProductRepository productRepository;
    private  final TransactionalWrapper transactionalWrapper;
    private  final CompanyRepository companyRepository;
    private  final ProductDomainRepository productDomainRepository;
    private  final CategoryRepository categoryRepository;
    private final ProductRequestResponseMapper productRequestResponseMapper;

    public ProductCommandService(ProductRepository productRepository,
                                 TransactionalWrapper transactionalWrapper, CompanyRepository companyRepository, ProductDomainRepository productDomainRepository, CategoryRepository categoryRepository, ProductRequestResponseMapper productRequestResponseMapper) {
        this.productRepository = productRepository;
        this.transactionalWrapper = transactionalWrapper;
        this.companyRepository = companyRepository;
        this.productDomainRepository = productDomainRepository;
        this.categoryRepository = categoryRepository;

        this.productRequestResponseMapper = productRequestResponseMapper;
    }

    public ProductResponse saveProductDomain(ProductRequest productRequest){

        ProductDomains productForSaving = productRequestResponseMapper.getProductForSaving(productRequest);

        List<String> companyIds = productRequest.getCompanyIds();
        List<String> productDomainIds = productRequest.getProductDomainIds();
        List<String> productCategoryIds = productRequest.getProductCategoryIds();

        Action<ProductDomains> action=(arangoDatabase, transactionId) -> {
            ProductDomains productDomains = productRepository.saveProductDomain(arangoDatabase, transactionId, productForSaving);

            List<ProductHasCompany> productHasCompanies = companyIds.stream().map(companyId -> {
                ProductHasCompany productHasCompany = new ProductHasCompany();
                productHasCompany.set_from(productDomains.getArangoId());
                productHasCompany.set_to(companyId);
                productHasCompany.setCreatedDate(LocalDateTime.now());
                productHasCompany.setModifiedDate(LocalDateTime.now());
                return productHasCompany;
            }).toList();

            List<ProductHasDomain> productHasDomains = productDomainIds.stream().map(productCategoryId -> {
                ProductHasDomain productHasDomain = new ProductHasDomain();
                productHasDomain.set_from(productDomains.getArangoId());
                productHasDomain.set_to(productCategoryId);
                productHasDomain.setCreatedDate(LocalDateTime.now());
                productHasDomain.setModifiedDate(LocalDateTime.now());
                return productHasDomain;
            }).toList();

            List<ProductHasCategory> productHasCategories = productCategoryIds.stream().map(productCategoryId -> {
                ProductHasCategory productHasCategory = new ProductHasCategory();
                productHasCategory.set_from(productDomains.getArangoId());
                productHasCategory.set_to(productCategoryId);
                productHasCategory.setCreatedDate(LocalDateTime.now());
                productHasCategory.setModifiedDate(LocalDateTime.now());
                return productHasCategory;
            }).toList();

            productHasCompanies.forEach(productHasCompany ->
                    companyRepository.saveProductHasCompany(arangoDatabase,transactionId,productHasCompany));

            productHasDomains.forEach(productHasDomain ->
                    productDomainRepository.saveProductDomain(arangoDatabase,transactionId,productHasDomain)
                    );

            productHasCategories.forEach(productHasCategory ->
                    categoryRepository.saveProductCategory(arangoDatabase,transactionId,productHasCategory));

            return productForSaving;
        };
        ProductDomains productDomains = transactionalWrapper.executeInsideTransaction(Set.of(VertexName.PRODUCT_DOMAIN,
                EdgeName.PRODUCT_HAS_COMPANY,
                PRODUCT_HAS_DOMAIN,
                PRODUCT_HAS_CATEGORY), action);
       return productRequestResponseMapper.getProductResponse(productDomains);

    }



}
