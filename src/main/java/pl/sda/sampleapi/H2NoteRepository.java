package pl.sda.sampleapi;

import org.springframework.data.repository.CrudRepository;
import pl.sda.sampleapi.models.Note;

import java.util.List;
import java.util.Optional;

public interface H2NoteRepository extends CrudRepository<Note, Long> {

    List<Note> findAllByCreator(String creator);

    Optional<Note> findByIdAndCreator(Long id, String creator);
}