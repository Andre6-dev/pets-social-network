package com.devandre.petsnetwork.repository;

import com.devandre.petsnetwork.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}