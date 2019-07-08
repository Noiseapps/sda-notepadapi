package pl.sda.sampleapi;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.sda.sampleapi.models.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findAllByCreator(String creator);

    Optional<Note> findByIdAndCreator(String id, String creator);
}