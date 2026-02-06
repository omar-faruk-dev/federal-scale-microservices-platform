package gov.platform.resourceservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResourceRequest(
    @NotBlank String name,
    @NotBlank String category,
    @Size(max = 1024) String description
) {
}
