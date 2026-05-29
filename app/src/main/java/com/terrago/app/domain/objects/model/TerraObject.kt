package com.terrago.app.domain.objects.model

data class TerraObject(
    val id: Long = 0L,
    val name: String,
    val description: String? = null,
    val length: Long? = null,
    val width: Long? = null,
    val height: Long? = null,
    val locationName: String? = null
)
