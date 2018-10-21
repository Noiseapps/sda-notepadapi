package pl.sda.sampleapi.models

import org.springframework.data.annotation.Id

data class Note(@Id val id: String? = null,
                val title: String,
                val content: String?,
                val creator: String?,
                val createdDate: Long)