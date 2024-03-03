package mukkak.bank.db.model;

import lombok.Getter;

@Getter
public enum CountryCode {
    // https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes
    IN("India");

    private final String countryName;

    CountryCode(String countryName) {
        this.countryName = countryName;
    }
}
