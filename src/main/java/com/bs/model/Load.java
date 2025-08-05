package com.bs.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loads")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Load booking entity representing a freight load")
public class Load {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Unique identifier for the load", example = "123e4567-e89b-12d3-a456-426614174000", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotBlank(message = "Shipper ID is required")
    @Size(max = 50, message = "Shipper ID must not exceed 50 characters")
    @Schema(description = "Unique identifier for the shipper", example = "SHIP001", required = true)
    private String shipperId;

    @NotBlank(message = "Loading point is required")
    @Size(max = 100, message = "Loading point must not exceed 100 characters")
    @Schema(description = "Location where the load will be picked up", example = "Mumbai, Maharashtra", required = true)
    private String loadingPoint;
    
    @NotBlank(message = "Unloading point is required")
    @Size(max = 100, message = "Unloading point must not exceed 100 characters")
    @Schema(description = "Destination where the load will be delivered", example = "Delhi, NCR", required = true)
    private String unloadingPoint;
    
    @Future(message = "Loading date must be in the future")
    @Schema(description = "Date and time when the load will be picked up", example = "2025-08-10T10:00:00", required = true)
    private LocalDateTime loadingDate;
    
    @Future(message = "Unloading date must be in the future")
    @Schema(description = "Date and time when the load will be delivered", example = "2025-08-12T18:00:00", required = true)
    private LocalDateTime unloadingDate;

    @NotBlank(message = "Product type is required")
    @Size(max = 50, message = "Product type must not exceed 50 characters")
    @Schema(description = "Type of product being transported", example = "Electronics", required = true)
    private String productType;
    
    @NotBlank(message = "Truck type is required")
    @Size(max = 50, message = "Truck type must not exceed 50 characters")
    @Schema(description = "Type of truck required for transportation", example = "Container", required = true)
    private String truckType;
    
    @Positive(message = "Number of trucks must be positive")
    @Schema(description = "Number of trucks required", example = "2", required = true)
    private int noOfTrucks;
    
    @Positive(message = "Weight must be positive")
    @Schema(description = "Total weight of the load in kilograms", example = "1500.0", required = true)
    private double weight;
    
    @Size(max = 500, message = "Comment must not exceed 500 characters")
    @Schema(description = "Additional comments or special instructions", example = "Handle with care - fragile items")
    private String comment;
    
    @Schema(description = "Date and time when the load was posted", example = "2025-08-05T14:30:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime datePosted;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Current status of the load", example = "POSTED", defaultValue = "POSTED")
    private Status status = Status.POSTED;

    @Schema(description = "Possible status values for a load")
    public enum Status {
        @Schema(description = "Load is posted and available for booking")
        POSTED, 
        @Schema(description = "Load has been booked by a carrier")
        BOOKED, 
        @Schema(description = "Load booking has been cancelled")
        CANCELLED
    }
}
