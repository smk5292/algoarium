package com.d204.algo.ui.ranking

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentRankingBinding
import com.d204.algo.presentation.viewmodel.RankingFragmentViewModel

class RankingFragment : BaseFragment<FragmentRankingBinding, BaseViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() =
            RankingFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun getViewBinding(): FragmentRankingBinding = FragmentRankingBinding.inflate(layoutInflater)
    override val viewModel: RankingFragmentViewModel by viewModels()
}
