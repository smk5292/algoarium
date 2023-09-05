package com.d204.algo.ui.recommend

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentRecommendBinding
import com.d204.algo.presentation.viewmodel.RecommendFragmentViewModel
import com.d204.algo.ui.ranking.RankingFragment

class RecommendFragment : BaseFragment<FragmentRecommendBinding, BaseViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance() =
            RankingFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun getViewBinding(): FragmentRecommendBinding = FragmentRecommendBinding.inflate(layoutInflater)
    override val viewModel: RecommendFragmentViewModel by viewModels()
}
