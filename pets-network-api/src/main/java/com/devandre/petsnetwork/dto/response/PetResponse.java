package com.devandre.petsnetwork.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetResponse {

    private Long id;
    private String name;
    private String species;
    private String breed;
    private String color;
    private String age;
    private String size;
    private String gender;
    private String goodWith;
    private String bio;
}
