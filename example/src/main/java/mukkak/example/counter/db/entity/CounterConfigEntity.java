package mukkak.example.counter.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mukkak.example.counter.db.model.EntityStatus;

@Entity
@Table(name = "counter_config")
@Data
@NoArgsConstructor
public class CounterConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "counter_config_seq")
    @SequenceGenerator(name = "counter_config_seq", sequenceName = "counter_config_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "counter_id")
    private Long counterId;

    @Column(name = "increment_size")
    private Integer incrementSize;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    public CounterConfigEntity(Boolean isDefault, Long counterId, Integer incrementSize) {
        this.id = null;
        this.isDefault = isDefault;
        this.counterId = counterId;
        this.incrementSize = incrementSize;
        this.status = EntityStatus.ACTIVE;
    }
}
