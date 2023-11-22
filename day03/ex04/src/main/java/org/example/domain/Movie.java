package org.example.domain;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
public class Movie extends Item{
    private String director;
    private String actor;
}
