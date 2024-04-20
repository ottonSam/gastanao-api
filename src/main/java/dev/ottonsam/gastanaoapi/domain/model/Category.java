package dev.ottonsam.gastanaoapi.domain.model;

import dev.ottonsam.gastanaoapi.domain.User.User;
import dev.ottonsam.gastanaoapi.domain.dto.CategoryRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(targetEntity = Expend.class, fetch = FetchType.EAGER, mappedBy = "category")
    private List<Expend> expends;

    public Category(CategoryRequestDTO data) {
        this.name = data.name();
        this.expends = new ArrayList<>();
    }
}
