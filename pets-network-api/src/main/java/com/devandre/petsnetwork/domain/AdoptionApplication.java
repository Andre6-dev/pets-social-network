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
@Table(name = "adoption_application")
public class AdoptionApplication extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "adoption_application_id_seq",
            sequenceName = "adoption_application_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "adoption_application_id_seq"
    )
    private Long id;

    private String status;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
