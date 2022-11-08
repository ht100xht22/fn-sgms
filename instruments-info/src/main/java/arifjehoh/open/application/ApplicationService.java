package arifjehoh.open.application;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    private final ApplicationRepository repository;

    public ApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Instrument> findById(Long id) {
        Instrument instrument = repository.findById(id).orElse(new Instrument());
        return ResponseEntity.ok(instrument);
    }
}
