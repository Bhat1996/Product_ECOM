package com.example.Product.Repository.edge;

import com.arangodb.ArangoDatabase;
import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.model.DocumentCreateOptions;
import com.arangodb.springframework.core.convert.ArangoConverter;
import com.arangodb.velocypack.VPackSlice;
import com.example.Product.domain.edge.ProductHasCategory;
import org.springframework.stereotype.Repository;

import static com.example.Product.metadata.EdgeName.PRODUCT_HAS_CATEGORY;

@Repository
public class CategoryRepository {

    private  final ArangoConverter arangoConverter;

    public CategoryRepository(ArangoConverter arangoConverter) {
        this.arangoConverter = arangoConverter;
    }

    public ProductHasCategory saveProductCategory(ArangoDatabase arangoDatabase,String transactionId,
                                                  ProductHasCategory productHasCategory){
        DocumentCreateEntity<VPackSlice> createEntity = arangoDatabase.collection(PRODUCT_HAS_CATEGORY).
                insertDocument(arangoConverter.write(productHasCategory), new DocumentCreateOptions()
                        .streamTransactionId(transactionId).returnNew(true));
        return arangoConverter.read(ProductHasCategory.class, createEntity.getNew());
    }
}
