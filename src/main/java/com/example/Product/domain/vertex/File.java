package com.example.Product.domain.vertex;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.example.Product.metadata.VertexName.FILE;

@Document(FILE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class File {
    List<String> image;
}
