package dev.ottonsam.gastanaoapi.domain.model;

import dev.ottonsam.gastanaoapi.domain.dto.ExpendRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_expend")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Expend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50, nullable = false)
    private String description;
    @Column(precision = 9, scale = 2, nullable = false)
    private BigDecimal expend_value;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    public Expend(ExpendRequestDTO data) {
        this.description = data.description();
        this.expend_value = data.expendValue();
    }


}