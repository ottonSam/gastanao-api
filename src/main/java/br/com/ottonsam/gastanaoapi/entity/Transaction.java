package br.com.ottonsam.gastanaoapi.entity;

import br.com.ottonsam.gastanaoapi.entity.dtos.CreateTransactionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private BigDecimal amount;
    private String description;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction(CreateTransactionDto createTransactionDto, User user, Category category) {
        this.user = user;
        this.category = category;
        this.amount = createTransactionDto.amount();
        this.description = createTransactionDto.description();
        this.date = LocalDate.now();
        this.type = createTransactionDto.type();
    }
}
