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

    public ResponseEntity<List<Lesson>> findAll() {
        List<Lesson> lessons = repository.findAll()
                .stream()
                .filter(Lesson::isBookAble)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lessons);
    }
}
