package mukkak.bank.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mukkak.bank.db.model.CountryCode;
import mukkak.bank.db.model.CustomerStatus;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "country")
    private CountryCode country;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CustomerStatus status;

    public CustomerEntity(CountryCode country, String name) {
        this.id = null;
        this.country = country;
        this.name = name;
        this.status = CustomerStatus.CREATED;
    }
}
