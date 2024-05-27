package com.devandre.petsnetwork.repository;

import com.devandre.petsnetwork.domain.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PetRepository extends JpaRepository<Pet, Long>, JpaSpecificationExecutor<Pet> {

    @Query(
            """
            SELECT pet
            FROM Pet pet
            WHERE pet.isAdopted = false
            AND pet.isNeutered = true
            AND pet.isSpayed = true
            """
    )
    Page<Pet> findAllAvailablePets(int page, int size);
}