package com.example.volatus.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.volatus.R

object CoilImage {
    @Composable
    fun loadImageCoil(url: String) : AsyncImagePainter {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = url)
                .apply(block = fun ImageRequest.Builder.() {
                    placeholder(R.drawable.placeholder)
                }).build()
        )
        return painter
    }
}