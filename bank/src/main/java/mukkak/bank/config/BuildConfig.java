package mukkak.bank.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class BuildConfig {
    @Value("${build.group}")
    private String group;

    @Value("${build.artifact}")
    private String artifact;

    @Value("${build.version}")
    private String version;
}
