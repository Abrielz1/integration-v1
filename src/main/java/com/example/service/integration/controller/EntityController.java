package com.example.service.integration.controller;

import com.example.service.integration.model.EntityModelResponseDto;
import com.example.service.integration.model.UpsertEntityRequestNewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/entity")
@RequiredArgsConstructor
public class EntityController {

    @GetMapping
    public ResponseEntity<List<EntityModelResponseDto>> entityList() {

        List<EntityModelResponseDto> entityModels = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            entityModels.add(EntityModelResponseDto.createMockModel("Model " + i));
        }

        return ResponseEntity.ok(entityModels);
    }

    @GetMapping("/{name}")
    public ResponseEntity<EntityModelResponseDto> entityByName(@PathVariable String name) {

        return ResponseEntity.ok(EntityModelResponseDto.createMockModel(name));
    }

    @PostMapping
    public ResponseEntity<EntityModelResponseDto> createEntity(@RequestBody UpsertEntityRequestNewDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(EntityModelResponseDto
                .createMockModel(request.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModelResponseDto> updateEntity(@PathVariable UUID id,
                                                               @RequestBody UpsertEntityRequestNewDto request) {

        return ResponseEntity.ok(new EntityModelResponseDto(id, request.getName(), Instant.now()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable UUID id) {

        log.info("Entity was deleted with id: {}", id);

        return ResponseEntity.noContent().build();
    }
}
