package br.com.ottonsam.gastanaoapi.entity.dtos;

import br.com.ottonsam.gastanaoapi.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResponseTransactionDto(UUID id, String description, String type, String category, LocalDate date, BigDecimal amount) {
    public ResponseTransactionDto(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getType().toString(),
                transaction.getCategory().getDescription(),
                transaction.getDate(),
                transaction.getAmount()
        );
    }
}