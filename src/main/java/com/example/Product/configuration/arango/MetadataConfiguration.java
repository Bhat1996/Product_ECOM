package com.example.Product.configuration.arango;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.CollectionType;
import com.arangodb.model.CollectionCreateOptions;
import com.example.Product.metadata.EdgeName;

import java.util.List;

import static com.example.Product.metadata.VertexName.namesAsList;

public class MetadataConfiguration {

    private final ArangoDatabase arangoDatabase;

    public MetadataConfiguration(ArangoDatabase arangoDatabase) {
        this.arangoDatabase = arangoDatabase;
    }

    public void createVertexCollections() {
        List<String> names = namesAsList();
        names.forEach(name -> {
            ArangoCollection arangoCollection = arangoDatabase.collection(name);
            if (!(arangoCollection.exists())) {
                arangoDatabase.createCollection(name);
            }
        });
    }

    public void createEdgeCollections() {
        List<String> names = EdgeName.namesAsList();
        names.forEach(name -> {
            ArangoCollection collection = arangoDatabase.collection(name);
            if (!(collection.exists())) {
                CollectionCreateOptions collectionCreateOptions = new CollectionCreateOptions();
                collectionCreateOptions.type(CollectionType.EDGES);
                arangoDatabase.createCollection(name, collectionCreateOptions);
            }
        });
    }

//    public void newsSearchView() {
//        ArangoView view = arangoDatabase.view(ViewName.NEWS_SEARCH);
//        if (not(view.exists())) {
//            ArangoSearchCreateOptions searchCreateOptions = new ArangoSearchCreateOptions();
//
//            CollectionLink newsCollectionLink = CollectionLink.on(NEWS)
//                    .includeAllFields(false)
//                    .analyzers("text_en")
//                    .fields(
//                            FieldLink.on("title")
//                                    .includeAllFields(true)
//                                    .analyzers("text_en"),
//                            FieldLink.on("description")
//                                    .includeAllFields(true)
//                                    .analyzers("text_en")
//
//                    );
//            searchCreateOptions.link(newsCollectionLink);
//            ViewEntity createdView = arangoDatabase.createArangoSearch(ViewName.NEWS_SEARCH, searchCreateOptions);
//            if (Objects.isNull(createdView)) {
//                throw new IllegalStateException("Error while creating View:: " + ViewName.NEWS_SEARCH);
//            }
//        }
//    }
}
