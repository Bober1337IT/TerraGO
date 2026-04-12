package com.terrago.app.presentation.shared.components.photo

data class PhotoPickerActions(
    val launchGallery: () -> Unit,
    val launchCamera: () -> Unit
)