package com.devandre.petsnetwork.mapper;

import com.devandre.petsnetwork.domain.Pet;
import com.devandre.petsnetwork.domain.enums.AgePetEnum;
import com.devandre.petsnetwork.domain.enums.GenderPetEnum;
import com.devandre.petsnetwork.domain.enums.SpeciesPetEnum;
import com.devandre.petsnetwork.dto.request.PetRegistrationRequest;
import com.devandre.petsnetwork.dto.response.PetResponse;
import com.devandre.petsnetwork.dto.response.PetResponseId;
import org.springframework.stereotype.Service;

@Service
public class PetMapper {

    public Pet toPetEntity(PetRegistrationRequest petRegistrationRequest) {
        return Pet.builder()
                .name(petRegistrationRequest.name())
                .species(SpeciesPetEnum.valueOf(petRegistrationRequest.species()))
                .breed(petRegistrationRequest.breed())
                .color(petRegistrationRequest.color())
                .age(AgePetEnum.valueOf(petRegistrationRequest.age()))
                .size(petRegistrationRequest.size())
                .gender(GenderPetEnum.valueOf(petRegistrationRequest.gender()))
                .goodWith(petRegistrationRequest.goodWith())
                .bio(petRegistrationRequest.bio())
                .isSpayed(petRegistrationRequest.isSpayed())
                .isNeutered(petRegistrationRequest.isNeutered())
                .picture(petRegistrationRequest.picture())
                .build();
    }

    public PetResponseId toPetResponse(Pet save) {
        return new PetResponseId(save.getId());
    }

    public PetResponse toPetResponseId(Pet pet) {
        return PetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .species(pet.getSpecies().name())
                .breed(pet.getBreed())
                .color(pet.getColor())
                .age(pet.getAge().name())
                .size(pet.getSize())
                .gender(pet.getGender().name())
                .goodWith(pet.getGoodWith())
                .bio(pet.getBio())
                .build();
    }

}
