package com.example.Product.domain.edge;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

import static com.example.Product.metadata.EdgeName.PRODUCT_HAS_CATEGORY;


@Edge(PRODUCT_HAS_CATEGORY)
@Getter
@Setter
public class ProductHasCategory {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String _from;
    private String _to;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
