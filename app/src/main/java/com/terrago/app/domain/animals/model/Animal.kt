package com.terrago.app.domain.animals.model

data class Animal(
    val id: Long = 0L,
    val objectId: Long,
    val speciesId: Long,
    val name: String? = null,
    val gender: String? = null,
    val birthDate: String? = null,
    val lastFeeding: String? = null,
    val lastSpray: String? = null,
    val lastMolt: String? = null,
    val size: Long? = null,
    val sizeType: Long? = null,
    val notes: String? = null,
    val photo: ByteArray? = null
)
