package com.terrago.app.ui.components.photo

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File

/**
 * Custom hook for capturing or picking photos.
 * Processes images to Full HD resolution with correct EXIF rotation.
 */
@Composable
fun rememberPhotoPicker(onPhotoCaptured: (ByteArray) -> Unit): PhotoPickerActions {
    val context = LocalContext.current

    // Create a temporary file to store the high-resolution camera output
    val tempFile = remember { File(context.externalCacheDir, "camera_capture.jpg") }
    val photoUri = remember {
        FileProvider.getUriForFile(context, "com.terrago.app.fileprovider", tempFile)
    }

    // Centralized processing logic
    val processAndSend = { uri: Uri ->
        try {
            val bytes = processPhotoUri(context, uri)
            if (bytes.isNotEmpty()) {
                onPhotoCaptured(bytes)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error processing image", Toast.LENGTH_SHORT).show()
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { processAndSend(it) }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            processAndSend(photoUri)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch(photoUri)
        } else {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    return PhotoPickerActions(
        launchGallery = { galleryLauncher.launch("image/*") },
        launchCamera = {
            val hasPermission = ContextCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED

            if (hasPermission) {
                cameraLauncher.launch(photoUri)
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    )
}