package com.devandre.petsnetwork.dto.request;

public record PetRegistrationDto(

        String name,
        String species,
        String breed,
        String color,
        String age,
        String size,
        String gender,

) {
}
