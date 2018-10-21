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
    fun getAllNotes(): MutableList<Note> {
        return noteRepo.findAll()
    }

    @PostMapping()
    fun createNote(@Valid @RequestBody command: CreateNoteCommand): Note {
        return noteRepo.save(Note(null, command.title, command.content, command.creator, Instant.now().toEpochMilli()))
    }

    @GetMapping("/{noteId}")
    fun getNote(@PathVariable noteId: String): Note {
        return noteRepo.findById(noteId).unwrap() ?: throw NotFoundException(noteId)
    }

    @DeleteMapping("/{noteId}")
    fun deleteNote(@PathVariable noteId: String) {
        noteRepo.findById(noteId).unwrap() ?: throw NotFoundException(noteId)
        noteRepo.deleteById(noteId)
    }

}