package arifjehoh.open.application;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository repository;

    public ApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<Instrument>> findAll() {
        List<Instrument> instruments = repository.findAll();
        return ResponseEntity.ok(instruments);
    }
}
