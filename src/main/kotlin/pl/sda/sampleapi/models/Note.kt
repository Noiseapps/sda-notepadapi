package pl.sda.sampleapi.models

import org.springframework.data.annotation.Id

data class Note(
        @field:Id @get:Id val id: String? = null,
        var title: String = "",
        var content: String? = null,
        val creator: String? = null,
        val createdDate: Long = System.currentTimeMillis())