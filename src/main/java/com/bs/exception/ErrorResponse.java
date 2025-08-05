package com.bs.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Error response model")
public class ErrorResponse {
    
    @Schema(description = "HTTP status code", example = "400")
    private int status;
    
    @Schema(description = "Error message", example = "Validation failed")
    private String message;
    
    @Schema(description = "Detailed error description", example = "Shipper ID is required")
    private String details;
    
    @Schema(description = "Timestamp when the error occurred", example = "2025-08-05T14:30:00")
    private LocalDateTime timestamp;
    
    @Schema(description = "API path where the error occurred", example = "/load")
    private String path;
}
