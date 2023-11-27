package hellojpa.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String name;

    private Integer price;

    private Integer stockAmount;

    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<>();
}
