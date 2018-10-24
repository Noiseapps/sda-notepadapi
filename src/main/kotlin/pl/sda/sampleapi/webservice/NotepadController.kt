package pl.sda.sampleapi.webservice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.sda.sampleapi.NoteRepository
import pl.sda.sampleapi.exception.NotFoundException
import pl.sda.sampleapi.models.CreateNoteCommand
import pl.sda.sampleapi.models.Note
import java.time.Instant
import java.util.*
import javax.validation.Valid

fun <T> Optional<T>.unwrap(): T? = orElse(null)

@RestController
@RequestMapping("notes")
class NotepadController(private val noteRepo: NoteRepository) {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onNoteNotFound(ex: NotFoundException) {
        println(ex.localizedMessage)
    }

    @GetMapping
    fun getAllNotes(@RequestParam creator: String): List<Note> {
        return noteRepo.findAllByCreator(creator)
    }

    @PostMapping()
    fun createNote(@Valid @RequestBody command: CreateNoteCommand): Note {
        return noteRepo.save(Note(null, command.title, command.content, command.creator, Instant.now().toEpochMilli()))
    }

    @PutMapping("/{noteId}")
    fun updateNote(@PathVariable noteId: String, @Valid @RequestBody command: CreateNoteCommand): Note {
        val unwrap = noteRepo.findById(noteId).unwrap()  ?: throw NotFoundException(noteId)
        unwrap.title = command.title
        unwrap.content = command.content
        return noteRepo.save(unwrap)
    }

    @DeleteMapping("/{noteId}")
    fun deleteNote(@PathVariable noteId: String) {
        noteRepo.findById(noteId).unwrap() ?: throw NotFoundException(noteId)
        noteRepo.deleteById(noteId)
    }

}