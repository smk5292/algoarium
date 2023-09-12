package com.d204.algo.ui.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentRankingBinding
import com.d204.algo.presentation.viewmodel.RankingFragmentViewModel
import com.d204.algo.ui.adapter.RankingAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
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

    @Inject
    lateinit var rankingAdapter: RankingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}
