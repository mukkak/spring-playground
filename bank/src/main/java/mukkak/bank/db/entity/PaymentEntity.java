package mukkak.bank.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mukkak.bank.db.model.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "wallet_id")
    private UUID walletId;

    @Enumerated(EnumType.STRING)
    @Column(name = "rail")
    private PaymentRail rail;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PaymentType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private PaymentLevel level;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private CurrencyCode currency;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "credit_wallet")
    private UUID creditWalletId;

    @Column(name = "debit_wallet")
    private UUID debitWalletId;

    @Column(name = "group_id")
    private UUID groupId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    public PaymentEntity(
        UUID walletId, PaymentRail rail, PaymentType type, PaymentLevel level,
        CurrencyCode currency, BigDecimal amount, UUID creditWalletId, UUID debitWalletId,
        UUID groupId
    ) {
        this.id = null;
        this.walletId = walletId;
        this.rail = rail;
        this.type = type;
        this.level = level;
        this.currency = currency;
        this.amount = amount;
        this.creditWalletId = creditWalletId;
        this.debitWalletId = debitWalletId;
        this.groupId = groupId;
    }
}
