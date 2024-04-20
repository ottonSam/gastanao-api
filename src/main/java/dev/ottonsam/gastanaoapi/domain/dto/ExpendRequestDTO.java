package dev.ottonsam.gastanaoapi.domain.dto;

import java.math.BigDecimal;

public record ExpendRequestDTO(String description, BigDecimal expendValue, Long CategoryId) {
}
