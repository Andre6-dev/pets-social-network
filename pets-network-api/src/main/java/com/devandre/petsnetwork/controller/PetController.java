package com.devandre.petsnetwork.controller;

import com.devandre.petsnetwork.controller.response.ControllerResponseHandler;
import com.devandre.petsnetwork.dto.request.PetRegistrationRequest;
import com.devandre.petsnetwork.service.PetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
@Tag(name = "Pets")
public class PetController {

    private final PetService service;

    @PostMapping
    public ResponseEntity<Object> savePet(
            @Valid @RequestBody PetRegistrationRequest request,
            Authentication connectedUser) {
        return ControllerResponseHandler.response(
                HttpStatus.CREATED,
                service.savePet(request, connectedUser),
                true
        );
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Object> getPet(
            @PathVariable Long petId
    ) {
        return ControllerResponseHandler.response(
                HttpStatus.OK,
                service.getPetById(petId),
                true
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllPets() {
        return ControllerResponseHandler.response(
                HttpStatus.OK,
                service.getAllPets(),
                true
        );
    }

}
