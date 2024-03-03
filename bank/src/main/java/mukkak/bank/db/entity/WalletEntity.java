package mukkak.bank.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mukkak.bank.db.model.CurrencyCode;
import mukkak.bank.db.model.WalletStatus;
import mukkak.bank.db.model.WalletType;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "customer_id")
    private UUID customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private WalletType type;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private CurrencyCode currency;

    @Column(name = "balance")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private WalletStatus status;

    public WalletEntity(UUID customerId, WalletType type, String name, CurrencyCode currency) {
        this.id = null;
        this.customerId = customerId;
        this.type = type;
        this.name = name;
        this.currency = currency;
        this.balance = BigDecimal.ZERO;
        this.status = WalletStatus.CREATED;
    }
}
