package com.moroz.persistence.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MovieEntity extends AbstractEntity implements com.moroz.persistence.entities.Entity {
    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
