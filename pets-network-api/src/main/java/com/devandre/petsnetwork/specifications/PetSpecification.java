package com.devandre.petsnetwork.specifications;

import com.devandre.petsnetwork.domain.Pet;
import org.springframework.data.jpa.domain.Specification;

public class PetSpecification {

    public static Specification<Pet> isAdopted(boolean isAdopted) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isAdopted"), isAdopted);
    }

}
