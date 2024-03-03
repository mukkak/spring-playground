package mukkak.example.core;

import lombok.extern.slf4j.Slf4j;
import mukkak.example.config.BuildConfig;
import mukkak.example.core.api.CommonApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(CommonApi.class)
@ActiveProfiles("test")
public class CommonApiTest {
    @MockBean
    private BuildConfig buildConfig;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testBuildInfo() throws Exception {
        when(buildConfig.getArtifactId()).thenReturn("example");
        when(buildConfig.getVersion()).thenReturn("0.0.1");

        mockMvc
            .perform(get("/info"))
//            .andDo(print())
            .andExpect(status().isOk());
    }
}
