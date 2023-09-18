package com.d204.algo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.d204.algo.base.BaseAdapter
import com.d204.algo.data.model.Status
import com.d204.algo.databinding.ItemStatusListBinding
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StatusAdapter @Inject constructor(
    private val glide: RequestManager,
    @ApplicationContext private val context: Context,
) : BaseAdapter<Status>() {
    // this(context) 때문에 override로 구현
    override val differ = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemStatusListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatusViewHolder(binding)
    }

    inner class StatusViewHolder(private val binding: ItemStatusListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Status> {
        override fun bind(item: Status) = with(binding) {
            itemStatusTextView.text = item.id.toString()
        }
    }
}
