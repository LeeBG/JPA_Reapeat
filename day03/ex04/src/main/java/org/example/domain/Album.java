package org.example.domain;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
public class Album extends Item{
    private String artist;
    private String etc;
}
