package com.devandre.petsnetwork.repository;

import com.devandre.petsnetwork.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}