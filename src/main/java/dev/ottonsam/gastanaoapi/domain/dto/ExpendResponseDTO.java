package dev.ottonsam.gastanaoapi.domain.dto;

import dev.ottonsam.gastanaoapi.domain.model.Expend;

import java.math.BigDecimal;

public record ExpendResponseDTO (Long id, String description, BigDecimal expendValue) {
    public ExpendResponseDTO(Expend expend) {
        this(expend.getId(), expend.getDescription(), expend.getExpend_value());
    }
}
