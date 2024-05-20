package com.devandre.petsnetwork.service;

import com.devandre.petsnetwork.domain.Pet;
import com.devandre.petsnetwork.domain.User;
import com.devandre.petsnetwork.dto.request.PetRegistrationRequest;
import com.devandre.petsnetwork.dto.response.PetResponse;
import com.devandre.petsnetwork.dto.response.PetResponseId;
import com.devandre.petsnetwork.mapper.PetMapper;
import com.devandre.petsnetwork.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public PetResponseId savePet(PetRegistrationRequest request, Authentication connectedUser) {
        log.info("Pet registration request received: {}", request);

        User user = ((User) connectedUser.getPrincipal());
        log.info("Connected user: {}", user);

        Pet pet = petMapper.toPetEntity(request);
        pet.setOwner(user);

        return petMapper.toPetResponse(petRepository.save(pet));
    }

    public PetResponse getPetById(Long petId) {
        log.info("Pet request received: {}", petId);

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found"));
        return petMapper.toPetResponseId(pet);
    }
}
