package pl.sda.sampleapi;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.sda.sampleapi.models.Note;

public interface NoteRepository extends MongoRepository<Note, String> {
}
