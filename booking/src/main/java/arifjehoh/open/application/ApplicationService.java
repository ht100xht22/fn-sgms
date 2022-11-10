package arifjehoh.open.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);
    private final ApplicationRepository repository;

    public ApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }

    protected <T> T mapFromJson(String entity, Class<T> target) throws JsonParseException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(entity, target);
    }

    public ResponseEntity<Booking> save(String input) {
        logger.info("Function have been made. Input: {}", input);
        if (input == null || input.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Form form = mapFromJson(input, Form.class);
            logger.info("Function have been made. Form: {}", form.toString());
            Booking booking = new Booking();
            booking.setLesson(form.getLesson());
            booking.setStudent(form.getStudent());
            logger.info("Function have been made. Booking: {}", booking.toString());
            booking = repository.save(booking);
            return ResponseEntity.ok(booking);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
