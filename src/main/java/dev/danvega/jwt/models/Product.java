package dev.danvega.jwt.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;

    private String fileType;
    private String productName;
    private String productDescription;
    private String productCategory;
    private Float productPrice;
    private String username;
    @Lob //stored as a blob
    private byte[] data;


    public Product(String contentType, String name, String desc, float price, byte[] bytes, String category, String username) {
        this.fileType = contentType;
        this.productName =name;
        this.productDescription =desc;
        this.productPrice = price;
        this.data = bytes;
        this.productCategory = category;
        this.username = username;

    }
}
