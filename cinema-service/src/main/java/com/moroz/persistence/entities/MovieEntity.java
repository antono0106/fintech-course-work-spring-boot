package com.moroz.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "movie")
@NoArgsConstructor
@Data
public class MovieEntity {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private Long mId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public MovieEntity(String name) {
        this.name = name;
    }
}
