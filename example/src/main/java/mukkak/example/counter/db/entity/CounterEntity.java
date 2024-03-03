package mukkak.example.counter.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mukkak.example.counter.db.model.EntityStatus;

@Entity
@Table(name = "counter")
@Data
@NoArgsConstructor
public class CounterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "counter_seq")
    @SequenceGenerator(name = "counter_seq", sequenceName = "counter_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Long count;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    public CounterEntity(String name) {
        this.id = null;
        this.name = name;
        this.count = 0L;
        this.status = EntityStatus.INACTIVE;
    }
}
