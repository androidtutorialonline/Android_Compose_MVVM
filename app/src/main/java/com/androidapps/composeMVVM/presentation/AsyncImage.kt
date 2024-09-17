package com.androidapps.composeMVVM.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.androidapps.composeMVVM.R

// Custom composable to use the extension
@Composable
fun GlobalAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    contentDescription: String? = null,
    error: Int = R.drawable.error_icon,
    contentScale: ContentScale = ContentScale.Crop,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true).build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
            .size(118.dp) // Size of the image
            .clip(CircleShape) // Clip image to a circular shape
            .width(120.dp)
            .aspectRatio(0.85f)
    ) {
        val state = this.painter.state
        when (state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            }

            is AsyncImagePainter.State.Error -> {
                Image(painterResource(id = error), contentDescription = contentDescription)
            }

            else -> {
                SubcomposeAsyncImageContent()
            }
        }
    }
}


