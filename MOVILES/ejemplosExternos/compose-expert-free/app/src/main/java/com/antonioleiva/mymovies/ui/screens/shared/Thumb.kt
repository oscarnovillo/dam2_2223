package com.antonioleiva.mymovies.ui.screens.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.filled.WifiProtectedSetup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.antonioleiva.mymovies.R
import com.antonioleiva.mymovies.model.MediaItem
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@ExperimentalCoilApi
@Composable
fun Thumb(mediaItem: MediaItem) {
    Box(
        modifier = Modifier
            .height(dimensionResource(R.dimen.cell_thumb_height))
            .fillMaxWidth()
    ) {
        val circularProgressDrawable = CircularProgressDrawable(LocalContext.current)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
//        CoilImage(
//            imageModel = mediaItem.thumb,
//            // Crop, Fit, Inside, FillHeight, FillWidth, None
//            contentScale = ContentScale.Crop,
//            // shows an image with a circular revealed animation.
//            circularReveal = CircularReveal(duration = 250),
//            // shows a placeholder ImageBitmap when loading.
//            //placeHolder = circularProgressDrawable,
//            // shows an error ImageBitmap when the request failed.
////            error = ImageBitmap.imageResource(R.drawable.ic_baseline_error_24)
////
//        )
      Image(
            painter = rememberImagePainter(
                data = mediaItem.thumb,
                builder = {
                    placeholder(circularProgressDrawable)
                }
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        if (mediaItem.type == MediaItem.Type.VIDEO) {
            Icon(
                Icons.Default.PlayCircleOutline,
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.cell_play_icon_size))
                    .align(Alignment.Center),
                tint = Color.White
            )
        }
    }
}