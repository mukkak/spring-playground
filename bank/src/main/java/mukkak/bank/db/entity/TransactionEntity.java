package mukkak.bank.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mukkak.bank.db.model.PaymentStatus;
import mukkak.bank.db.model.TransactionType;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "wallet")
    private UUID walletId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    public TransactionEntity(UUID paymentId, UUID walletId, TransactionType type) {
        this.id = id;
        this.paymentId = paymentId;
        this.walletId = walletId;
        this.type = type;
        this.status = PaymentStatus.CREATED;
    }
}
