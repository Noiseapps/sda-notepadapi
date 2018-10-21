package pl.sda.sampleapi.exception

import java.lang.Exception

class NotFoundException(noteId: String) : Exception("Note $noteId not found")