package com.bs.controller;

import com.bs.model.Load;
import com.bs.services.LoadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/load")
@Tag(name = "Load Management", description = "APIs for managing load bookings")
public class LoadController {

    @Autowired
    private LoadService loadService;

    @Operation(summary = "Create a new load", description = "Creates a new load booking with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Load created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Load.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Load> createLoad(
            @Parameter(description = "Load details to be created", required = true)
            @Valid @RequestBody Load load) {
        Load created = loadService.createLoad(load);
        return ResponseEntity.ok(created);
    }

//    // GET /load?shipperId=&truckType=&status=&page=0&size=10
//    @GetMapping
//    public ResponseEntity<Page<Load>> getAllLoads(
//            @RequestParam(required = false) String shipperId,
//            @RequestParam(required = false) String truckType,
//            @RequestParam(required = false) String status,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Load> loads = loadService.findLoads(shipperId, truckType, status, pageable);
//        return ResponseEntity.ok(loads);
//    }

    @Operation(summary = "Get load by ID", description = "Retrieves a specific load by its unique identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Load found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Load.class))),
        @ApiResponse(responseCode = "404", description = "Load not found"),
        @ApiResponse(responseCode = "400", description = "Invalid ID format")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Load> getLoadById(
            @Parameter(description = "Load ID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID id) {
        Optional<Load> load = loadService.getLoadById(id);
        return load.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update load", description = "Updates an existing load with new information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Load updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Load.class))),
        @ApiResponse(responseCode = "404", description = "Load not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Load> updateLoad(
            @Parameter(description = "Load ID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID id,
            @Parameter(description = "Updated load details", required = true)
            @Valid @RequestBody Load load) {

        Load updated = loadService.updateLoad(id, load);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete load", description = "Deletes a load booking")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Load deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Load not found"),
        @ApiResponse(responseCode = "400", description = "Invalid ID format")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoad(
            @Parameter(description = "Load ID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID id) {
        loadService.deleteLoad(id);
        return ResponseEntity.noContent().build();
    }
}
