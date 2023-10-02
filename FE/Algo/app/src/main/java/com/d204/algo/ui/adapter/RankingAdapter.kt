package com.d204.algo.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.d204.algo.R
import com.d204.algo.base.BaseAdapter
import com.d204.algo.data.model.Ranking
import com.d204.algo.databinding.ItemRankingListBinding
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.random.Random


// ApplicationContext는 적절한 종속성을 주입함 (여기서는 Activity의 context를 기대)
class RankingAdapter @Inject constructor(
    private val glide: RequestManager,
    @ApplicationContext private val context: Context,
) : BaseAdapter<Ranking>() {
    // this(context) 때문에 override로 구현
    override val differ = AsyncListDiffer(this, diffCallback)
    private var lastPosition = -1

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRankingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RankingViewHolder(binding)
    }

    inner class RankingViewHolder(private val binding: ItemRankingListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Ranking> {
        override fun bind(item: Ranking) {
            binding.apply {
                rankingListItemStart.setImageResource(selectRandomImg())
                rankingListItemRank.text = item.ranking.toString()
                rankingListItemPoint.text = item.score.toString()
                rankingListItemName.text = item.kakaoNickname
                rankingListItemName.isSelected = true
                setAnimation(binding, adapterPosition)
                root.setOnClickListener {
                    onItemClickListener?.let { itemClick ->
                        itemClick(item)
                    }
                }
            }
        }
    }

    private fun selectRandomImg(): Int {
        val randomResourceId = when (Random.nextInt(5)) {
            0 -> R.drawable.fish1
            1 -> R.drawable.fish2
            2 -> R.drawable.fish3
            3 -> R.drawable.fish4
            else -> R.drawable.fish5
        }
        return randomResourceId
    }

    private fun setAnimation(binding: ItemRankingListBinding, position: Int) {
        val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_shake_left)
        when(position) {
            in 0..3 -> animation.startOffset = 150L*position
            else -> animation.startOffset = 100L
        }
        // 애니메이션 리스너 설정
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                with(binding) {
                    rankingListItemStart.alpha = 0f
                    rankingListItemMid.alpha = 0f
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            binding.root.startAnimation(animation)
            lastPosition = position
        }
    }
}
