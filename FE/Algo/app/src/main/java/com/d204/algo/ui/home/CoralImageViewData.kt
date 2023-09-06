package com.d204.algo.ui.home

import android.widget.ImageView
import java.util.Random

data class CoralImageViewData(
    val imageView: ImageView,
    val speed: Float = Random().nextFloat() * 10
)