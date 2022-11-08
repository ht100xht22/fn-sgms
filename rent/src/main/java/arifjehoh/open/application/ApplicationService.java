package arifjehoh.open.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class ApplicationService {
    private final ApplicationRepository repository;

    public ApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }
    protected <T> T mapFromJson(String entity, Class<T> target) throws JsonParseException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(entity, target);
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity save(String input) {
        if (input == null || input.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Rent rent;
        try {
            Form form = mapFromJson(input, Form.class);
            rent = repository.save(new Rent(form));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(rent);
    }
}
