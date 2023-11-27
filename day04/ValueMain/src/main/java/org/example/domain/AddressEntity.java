package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "address")
@NoArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String zipCode;


    public AddressEntity(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
