package mukkak.bank.db.model;

import lombok.Getter;

@Getter
public enum CurrencyCode {
    // https://en.wikipedia.org/wiki/ISO_4217
    INR("Indian Rupee");

    private final String currencyName;

    CurrencyCode(String currencyName) {
        this.currencyName = currencyName;
    }
}
