package br.com.ottonsam.gastanaoapi.entity.dtos;

import br.com.ottonsam.gastanaoapi.entity.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransactionDto(UUID categoryId, BigDecimal amount, String description, TransactionType type) {
}
