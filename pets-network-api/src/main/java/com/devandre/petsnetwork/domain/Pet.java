package com.devandre.petsnetwork.domain;

import com.devandre.petsnetwork.common.BaseEntity;
import com.devandre.petsnetwork.domain.enums.AgePetEnum;
import com.devandre.petsnetwork.domain.enums.GenderPetEnum;
import com.devandre.petsnetwork.domain.enums.SpeciesPetEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "pet_id_seq",
            sequenceName = "pet_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pet_id_seq"
    )
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SpeciesPetEnum species;

    private String breed;

    private String color;

    @Enumerated(EnumType.STRING)
    private AgePetEnum age;

    private String size;

    @Enumerated(EnumType.STRING)
    private GenderPetEnum gender;

    private String goodWith;

    private String bio;

    private boolean isAdopted;

    private boolean isSpayed;

    private boolean isNeutered;

    private Long adoptedBy;

    private String picture;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "pet")
    private List<Comment> comments;

    @OneToMany(mappedBy = "pet")
    private List<AdoptionApplication> adoptionApplications;

    @Transient
    public double getRate() {
        if (comments == null || comments.isEmpty()) {
            return 0.0;
        }
        var rate = this.comments.stream()
                .mapToDouble(Comment::getNote)
                .average()
                .orElse(0.0);

        // Return 4.0 if roundedRate is less than 4.5, otherwise return 4.5
        return Math.round(rate * 100.0) / 100.0;
    }

}
