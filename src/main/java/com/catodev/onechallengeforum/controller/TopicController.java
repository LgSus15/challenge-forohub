package com.catodev.onechallengeforum.controller;

import com.catodev.onechallengeforum.dto.topic.TopicCreateDto;
import com.catodev.onechallengeforum.dto.topic.TopicResponseDto;
import com.catodev.onechallengeforum.dto.topic.TopicUpdateDto;
import com.catodev.onechallengeforum.service.topic.TopicService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/topics")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Tópicos", description = "Endpoints para la gestión de tópicos del foro")
public class TopicController {
    private final TopicService topicService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un nuevo tópico", description = "Registra un nuevo tópico en la base de datos asociado a un usuario y curso.")
    public TopicResponseDto create(@Valid @RequestBody TopicCreateDto dto) {
        return topicService.create(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar tópicos", description = "Obtiene una lista paginada de todos los tópicos ordenados por fecha de creación.")
    public Page<TopicResponseDto> findAll(@PageableDefault(size = 10, sort = { "creationDate" }) Pageable pageable) {
        return topicService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tópico por ID", description = "Obtiene los detalles de un tópico específico mediante su ID.")
    public TopicResponseDto findById(@PathVariable @Min(1) Long id) {
        return topicService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tópico", description = "Modifica los datos de un tópico existente (título, mensaje, estado).")
    public TopicResponseDto update(@PathVariable @Min(1) Long id, @Valid @RequestBody TopicUpdateDto dto) {
        return topicService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar tópico", description = "Elimina de forma permanente un tópico especificando su ID.")
    public void delete(@PathVariable @Min(1) Long id) {
        topicService.delete(id);
    }
}
