package org.example.domain;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
public class Book extends Item{
    private String author;
    // isbn번호
    private String isbn;
}

