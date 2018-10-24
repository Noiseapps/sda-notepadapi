package pl.sda.sampleapi.models

import org.springframework.data.annotation.Id

data class Note(@Id val id: String? = null,
                var title: String,
                var content: String?,
                val creator: String?,
                val createdDate: Long)