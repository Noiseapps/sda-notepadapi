package pl.sda.sampleapi.models

import javax.validation.constraints.NotBlank

data class CreateNoteCommand(
        @NotBlank val title: String,
        @NotBlank val content: String)