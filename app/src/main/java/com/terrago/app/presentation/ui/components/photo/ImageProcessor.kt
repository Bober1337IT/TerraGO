package com.terrago.app.presentation.ui.components.photo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import androidx.core.graphics.scale
import java.io.ByteArrayOutputStream

/**
 * Handles heavy lifting: decoding, rotating based on EXIF, and scaling to Full HD.
 */
fun processPhotoUri(context: Context, uri: Uri): ByteArray {
    val inputStream = context.contentResolver.openInputStream(uri) ?: return byteArrayOf()

    // Decode original bitmap
    val originalBitmap = BitmapFactory.decodeStream(inputStream)
    inputStream.close()

    if (originalBitmap == null) return byteArrayOf()

    // Fix rotation using EXIF data
    val rotation = getRotation(context, uri)
    val rotatedBitmap = if (rotation != 0) {
        val matrix = Matrix().apply { postRotate(rotation.toFloat()) }
        Bitmap.createBitmap(
            originalBitmap, 0, 0, originalBitmap.width, originalBitmap.height, matrix, true
        )
    } else {
        originalBitmap
    }

    // Scale to Full HD (max 1920px on the longest side)
    val scaledBitmap = scaleBitmap(rotatedBitmap, 1920)

    // Compress to JPEG (Quality 90 is a sweet spot for HD)
    val outputStream = ByteArrayOutputStream()
    scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)

    // Cleanup
    if (rotatedBitmap != originalBitmap) rotatedBitmap.recycle()
    if (scaledBitmap != rotatedBitmap) scaledBitmap.recycle()
    originalBitmap.recycle()

    return outputStream.toByteArray()
}

private fun getRotation(context: Context, uri: Uri): Int {
    return try {
        context.contentResolver.openInputStream(uri)?.use { stream ->
            val exif = ExifInterface(stream)
            when (exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )) {
                ExifInterface.ORIENTATION_ROTATE_90 -> 90
                ExifInterface.ORIENTATION_ROTATE_180 -> 180
                ExifInterface.ORIENTATION_ROTATE_270 -> 270
                else -> 0
            }
        } ?: 0
    } catch (e: Exception) {
        0
    }
}

private fun scaleBitmap(bitmap: Bitmap, maxSide: Int): Bitmap {
    val currentWidth = bitmap.width
    val currentHeight = bitmap.height

    val scale = maxSide.toFloat() / maxOf(currentWidth, currentHeight)

    // Only downscale if the image is larger than the target
    if (scale >= 1f) return bitmap

    return bitmap.scale((currentWidth * scale).toInt(), (currentHeight * scale).toInt())
}