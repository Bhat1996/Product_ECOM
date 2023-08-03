package com.example.Product.Repository;

import com.arangodb.ArangoDatabase;
import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.model.DocumentCreateOptions;
import com.arangodb.springframework.core.convert.ArangoConverter;
import com.arangodb.velocypack.VPackSlice;
import com.example.Product.domain.ProductDomains;
import org.springframework.stereotype.Repository;

import static com.example.Product.metadata.VertexName.PRODUCT_DOMAIN;

@Repository
public class ProductRepository {

    private  final ArangoConverter arangoConverter;

    public ProductRepository(ArangoConverter arangoConverter) {
        this.arangoConverter = arangoConverter;

    }

    public ProductDomains saveProductDomain(ArangoDatabase arangoDatabase, String transactionId, ProductDomains productDomains){
        DocumentCreateEntity<VPackSlice> createEntity = arangoDatabase.collection(PRODUCT_DOMAIN).
                insertDocument(arangoConverter.write(productDomains),
                new DocumentCreateOptions().streamTransactionId(transactionId).returnNew(true));

       return arangoConverter.read(ProductDomains.class,createEntity.getNew());
    }
}
