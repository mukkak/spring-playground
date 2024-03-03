package mukkak.bank.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class BankConfig {
    @Value("${bank.name}")
    private String name;
}
