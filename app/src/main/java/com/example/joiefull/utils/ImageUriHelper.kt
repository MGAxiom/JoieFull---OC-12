package com.example.joiefull.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.example.joiefull.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL

@Composable
fun ShareButton(
    text: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
) {
    val context = LocalContext.current
    var isSharing by remember { mutableStateOf(false) }

    if (isSharing) {
        // Launch the download and sharing in a coroutine
        LaunchedEffect(Unit) {
            val uri = downloadImageAndGetUri(context, imageUrl)
            val shareIntent =
                Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_STREAM, uri)
                    putExtra(Intent.EXTRA_TEXT, text)
                    type = "image/*"
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
            context.startActivity(Intent.createChooser(shareIntent, null))
            isSharing = false
        }
    }
    IconButton(
        onClick = {
            isSharing = true
        },
        modifier =
            modifier
                .background(Color.White.copy(alpha = 0.3f), CircleShape)
                .size(36.dp)
                .background(color = backgroundColor, shape = CircleShape)
                .clip(CircleShape),
        // Permet d'éviter d'avoir une ombre carré lors du clic
    ) {
        Icon(
            painter = painterResource(R.drawable.outline_share_24),
            contentDescription = "Navigate back",
            tint = Color.Black,
            modifier = Modifier.size(15.dp),
        )
    }
}

suspend fun downloadImageAndGetUri(
    context: Context,
    imageUrl: String,
): android.net.Uri? {
    return withContext(Dispatchers.IO) {
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection()
            connection.connect()
            val input = connection.getInputStream()
            val bitmap = BitmapFactory.decodeStream(input)
            input.close()

            val file = File(context.cacheDir, "shared_image.png")
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
            out.close()

            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
