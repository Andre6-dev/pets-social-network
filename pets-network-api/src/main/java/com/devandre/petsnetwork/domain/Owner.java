package com.devandre.petsnetwork.domain;

import com.devandre.petsnetwork.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "owner_id_seq",
            sequenceName = "owner_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_id_seq"
    )
    private Long id;

    private String fullName;

    private String email;

    private String dni;

    private String phoneNumber;

    private String address;

    private String city;

    private String state;

    private LocalDateTime birthDate;

    private LocalDateTime startAdoptionApplicationDate;

    private boolean isAdopter;
}
