package com.example.myfirstapp.model

import java.util.UUID

data class Note(
    val id: Long = UUID.randomUUID().mostSignificantBits,
    var title: String = "",
    var content: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
