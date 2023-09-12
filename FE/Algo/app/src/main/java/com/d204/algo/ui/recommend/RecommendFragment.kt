package com.d204.algo.ui.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentRecommendBinding
import com.d204.algo.presentation.viewmodel.RecommendFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecommendFragment : BaseFragment<FragmentRecommendBinding, BaseViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance() =
            RecommendFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun getViewBinding(): FragmentRecommendBinding = FragmentRecommendBinding.inflate(layoutInflater)
    override val viewModel: RecommendFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}
