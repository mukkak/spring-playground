## Models

```
Customer
|- Wallet[] (owns)

Payment
|- Wallet (credit)
|- Wallet (debit)
```

```
Customer(
    UUID id,
    String name,
    String country,
    CustomerStatus status,
)

Wallet(
    UUID id,
    UUID customerId,
    String currency,
    BigDecimal balance,
    WalletType type,
    WalletStatus status,
)

Payment(
    UUID id,
    UUID walletId,
    String currency,
    BigDecimal amount,
    UUID creditWalletId,
    UUID debitWalletId,
    UUID groupId,
    PaymentLevel level,
    PaymentRail rail,
    PaymentStatus status,
)
```
