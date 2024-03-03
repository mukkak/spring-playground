package mukkak.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class BuildConfig {
    @Value("${build.groupId}")
    private String groupId;

    @Value("${build.artifactId}")
    private String artifactId;

    @Value("${build.version}")
    private String version;
}
