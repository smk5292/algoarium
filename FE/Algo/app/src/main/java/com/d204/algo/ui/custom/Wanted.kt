package com.d204.algo.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.d204.algo.R
import com.d204.algo.databinding.CustomViewWantedBinding

class Wanted @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: CustomViewWantedBinding = CustomViewWantedBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        inflate(context, R.layout.custom_view_wanted, this)
    }

    fun setWantedImage(resourceId: Int) {
        binding.wantedProfileImg.setImageResource(resourceId)
    }

    fun setWantedName(name: String) {
        binding.wantedName.text = name
    }

    fun setWantedCost(cost: String) {
        binding.wantedCost.text = cost
    }
}
