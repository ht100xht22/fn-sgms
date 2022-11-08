package arifjehoh.open.application;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {
    private final ApplicationRepository repository;

    public ApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Student> findById(Long id) {
        Optional<Student> student = repository.findById(id);
        ResponseEntity<Student> response = ResponseEntity.of(student);
        return response;
    }
}