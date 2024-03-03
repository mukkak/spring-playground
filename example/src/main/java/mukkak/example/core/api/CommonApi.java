package mukkak.example.core.api;

import mukkak.example.config.BuildConfig;
import mukkak.example.core.model.BuildInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonApi {
    private final BuildConfig buildConfig;

    public CommonApi(BuildConfig buildConfig) {
        this.buildConfig = buildConfig;
    }

    @GetMapping("/info")
    public BuildInfo getBuildInfo() {
        return new BuildInfo(buildConfig.getArtifactId(), buildConfig.getVersion());
    }
}
