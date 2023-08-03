package com.example.Product.Repository.edge;

import com.arangodb.ArangoDatabase;
import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.model.DocumentCreateOptions;
import com.arangodb.springframework.core.convert.ArangoConverter;
import com.arangodb.velocypack.VPackSlice;
import com.example.Product.domain.edge.ProductHasCompany;
import org.springframework.stereotype.Repository;

import static com.example.Product.metadata.EdgeName.PRODUCT_HAS_COMPANY;

@Repository
public class CompanyRepository {

    private  final ArangoConverter arangoConverter;

    public CompanyRepository(ArangoConverter arangoConverter) {
        this.arangoConverter = arangoConverter;
    }

    public ProductHasCompany saveProductHasCompany(ArangoDatabase arangoDatabase,
                                                   String transactionId, ProductHasCompany productHasCompany){

        DocumentCreateEntity<VPackSlice> createEntity = arangoDatabase.collection(PRODUCT_HAS_COMPANY).
                insertDocument(arangoConverter.write(productHasCompany), new DocumentCreateOptions()
                        .streamTransactionId(transactionId).returnNew(true));

        return arangoConverter.read(ProductHasCompany.class,createEntity.getNew());
    }
}
