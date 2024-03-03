package mukkak.example;

import mukkak.example.counter.db.CounterDbOps;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

//    @Bean
//    public CommandLineRunner init(CounterDbOps counterDbOps) {
//        return (args) -> {
//            if (counterDbOps.readDefaultCounterConfig().isEmpty()) {
//                counterDbOps.addOrUpdateDefaultCounterConfig(2);
//            }
//        };
//    }
}
