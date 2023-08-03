package com.example.Product.Repository.edge;

import com.arangodb.ArangoDatabase;
import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.model.DocumentCreateOptions;
import com.arangodb.springframework.core.convert.ArangoConverter;
import com.arangodb.velocypack.VPackSlice;
import com.example.Product.domain.edge.ProductHasDomain;
import org.springframework.stereotype.Repository;

import static com.example.Product.metadata.EdgeName.PRODUCT_HAS_DOMAIN;

@Repository
public class ProductDomainRepository {

    private final ArangoConverter arangoConverter;

    public ProductDomainRepository(ArangoConverter arangoConverter) {
        this.arangoConverter = arangoConverter;
    }

    public ProductHasDomain saveProductDomain(ArangoDatabase arangoDatabase, String transactionId,
                                              ProductHasDomain productHasDomain){
        DocumentCreateEntity<VPackSlice> createEntity = arangoDatabase.collection(PRODUCT_HAS_DOMAIN).
                insertDocument(arangoConverter.write(productHasDomain), new DocumentCreateOptions()
                        .streamTransactionId(transactionId).returnNew(true));
        return arangoConverter.read(ProductHasDomain.class,createEntity.getNew());
    }
}
