package pl.sda.sampleapi.webservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.sampleapi.H2NoteRepository;
import pl.sda.sampleapi.SampleapiApplication;
import pl.sda.sampleapi.models.CreateNoteCommand;
import pl.sda.sampleapi.models.Note;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SampleapiApplication.class)
public class NotepadControllerTest {

    @Autowired
    H2NoteRepository noteRepository;

    private final String creator = UUID.randomUUID().toString();
    private NotepadController notepadController;

    @BeforeEach
    public void setup() {
        notepadController = new NotepadController(noteRepository);
    }

    @Test
    public void testNewNoteAdded() {
        String title = UUID.randomUUID().toString();
        String content = UUID.randomUUID().toString();

        Note createdNote = notepadController.createNote(new CreateNoteCommand(title, content), creator);

        assertNotNull(createdNote);
        assertNotNull(createdNote.getId());
        assertEquals(title, createdNote.getTitle());
        assertEquals(content, createdNote.getContent());
        assertEquals(creator, createdNote.getCreator());
    }
}
