package com.d204.algo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
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
}
