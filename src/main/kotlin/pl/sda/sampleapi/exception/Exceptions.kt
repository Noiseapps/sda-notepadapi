package pl.sda.sampleapi.exception

import java.lang.Exception

class NotFoundException(noteId: Long) : Exception("Note $noteId not found")