package com.devandre.petsnetwork.dto.request;

public record PetRegistrationRequest(
        String name,
        String species,
        String breed,
        String color,
        String age,
        String size,
        String gender,
        String goodWith,
        String bio,
        Boolean isSpayed,
        Boolean isNeutered,
        String picture
) {
}
