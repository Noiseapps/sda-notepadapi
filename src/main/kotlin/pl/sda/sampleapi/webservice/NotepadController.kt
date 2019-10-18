package pl.sda.sampleapi.webservice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.sda.sampleapi.H2NoteRepository
import pl.sda.sampleapi.exception.NotFoundException
import pl.sda.sampleapi.models.CreateNoteCommand
import pl.sda.sampleapi.models.Note
import java.util.*
import javax.validation.Valid

fun <T> Optional<T>.unwrap(): T? = orElse(null)

@RestController
@RequestMapping("notes")
class NotepadController(private val noteRepo: H2NoteRepository) {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onNoteNotFound(ex: NotFoundException) {
        println(ex.localizedMessage)
    }

    @GetMapping
    fun getAllNotes(@RequestHeader("creator-name") creator: String): List<Note> {
        return noteRepo.findAllByCreator(creator)
    }

    @PostMapping
    fun createNote(@Valid @RequestBody command: CreateNoteCommand, @RequestHeader("creator-name") creator: String): Note {
        return noteRepo.save(Note(command.title, command.content, creator))
    }

    @PutMapping("/{noteId}")
    fun updateNote(@PathVariable noteId: Long, @Valid @RequestBody command: CreateNoteCommand, @RequestHeader("creator-name") creator: String): Note {
        val unwrap = noteRepo.findByIdAndCreator(noteId, creator).unwrap() ?: throw NotFoundException(noteId)
        unwrap.title = command.title
        unwrap.content = command.content
        return noteRepo.save(unwrap)
    }

    @DeleteMapping("/{noteId}")
    fun deleteNote(@PathVariable noteId: Long, @RequestHeader("creator-name") creator: String) {
        noteRepo.findByIdAndCreator(noteId, creator).unwrap() ?: throw NotFoundException(noteId)
        noteRepo.deleteById(noteId)
    }

}