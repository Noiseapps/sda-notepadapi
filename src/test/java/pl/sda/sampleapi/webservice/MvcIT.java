package pl.sda.sampleapi.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.sampleapi.models.CreateNoteCommand;
import pl.sda.sampleapi.models.Note;

import java.util.logging.Logger;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MvcIT {

    private static final String SAMPLE_CREATOR = "asdf";
    Logger logger = Logger.getLogger("MVC_TEST");

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testAddNote() throws Exception {
        CreateNoteCommand createNoteCommand = new CreateNoteCommand("Test title", "Test content");


        String result = mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("creator-name", SAMPLE_CREATOR)
                .content(objectMapper.writeValueAsString(createNoteCommand)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        logger.info(result);

        Note note = objectMapper.readValue(result, Note.class);
        Assertions.assertEquals("Test title", note.getTitle());
        Assertions.assertEquals("Test content", note.getContent());
        Assertions.assertNotNull(note.getCreatedDate());
        Assertions.assertEquals(1, note.getId());
        Assertions.assertEquals(SAMPLE_CREATOR, note.getCreator());
    }
}
