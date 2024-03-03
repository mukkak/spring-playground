package mukkak.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ExampleConfig {
    @Value("${example.secret}")
    private String secret;
}
