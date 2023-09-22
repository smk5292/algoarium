package com.d204.algo.ui.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.d204.algo.presentation.viewmodel.RecommendUIModel

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(
        this,
        {
            it?.let { t -> observer(t) }
        }
    )
}

fun <T> LifecycleOwner.observe(liveData: MutableLiveData<T>, observer: (T) -> Unit) {
    liveData.observe(
        this,
        {
            it?.let { t -> observer(t) }
        }
    )
}
