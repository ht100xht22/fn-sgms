package arifjehoh.open.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Function;

@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private final ApplicationService service;

    public Application(ApplicationService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public Function<String, ResponseEntity<List<Instrument>>> function() {
        return value -> {
            logger.info("Function have been made. Value: {}", value);
            return service.findAll();
        };
    }
}
