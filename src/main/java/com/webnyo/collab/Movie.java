package com.webnyo.collab;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Movie {
    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    private String name;

    private String year;

    public Movie(String name, String year) {
        this.name = name;
        this.year = year;
    }
}
