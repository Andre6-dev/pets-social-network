package com.devandre.petsnetwork.domain;

import com.devandre.petsnetwork.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Articles extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "articles_id_seq",
            sequenceName = "articles_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "articles_id_seq"
    )
    private Long id;

    private String title;

    private String description;

    private String image;
}
