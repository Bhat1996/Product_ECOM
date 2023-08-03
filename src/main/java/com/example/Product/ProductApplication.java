package com.example.Product;

import com.arangodb.ArangoDatabase;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.core.ArangoOperations;
import com.example.Product.configuration.arango.MetadataConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.event.EventListener;

@SpringBootApplication
//@ConfigurationPropertiesScan(basePackages = "com.example.Product.configuration.properties")
@EnableArangoRepositories
public class ProductApplication {

	@Value("${arango.db.name}")
	private String dbName;

	private  final ArangoOperations arangoOperations;

	public ProductApplication(ArangoOperations arangoOperations) {
		this.arangoOperations = arangoOperations;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void configureMetadata(){
		ArangoDatabase db = arangoOperations.driver().db(dbName);
		MetadataConfiguration metadataConfiguration = new MetadataConfiguration(db);
		metadataConfiguration.createVertexCollections();
		metadataConfiguration.createEdgeCollections();
	}
}
