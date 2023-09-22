package com.d204.algo.ui.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.d204.algo.ApplicationClass
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.data.model.Problem
import com.d204.algo.databinding.FragmentRecommendBinding
import com.d204.algo.presentation.utils.Constants
import com.d204.algo.presentation.viewmodel.RecommendFragmentViewModel
import com.d204.algo.presentation.viewmodel.RecommendUIModel
import com.d204.algo.ui.custom.RecommendProblemView
import com.d204.algo.ui.extension.observe
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
    private val espHelper = ApplicationClass.preferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 뷰모델 변수에 옵저빙을 등록한다.
        observe(viewModel.getStrongs(), ::onViewStateChange)
        observe(viewModel.getWeaks(), ::onViewStateChange)
        observe(viewModel.getSimilars(), ::onViewStateChange)
        
        // 각 30개의 추천 문제 리스트를 서버에서 가져온다 -> 변경되면 observe에서 인지하고 binding ui를 갱신
        viewModel.getStrongList(espHelper.prefUserId)
        viewModel.getWeakList(espHelper.prefUserId)
        viewModel.getSimilarList(espHelper.prefUserId)

        with(binding) {
            // setProblem(recommendStrong1, viewModel.strongList.value?.)
        }
    }

    private fun setProblem(v: RecommendProblemView, problem: Problem) {
        val curResource = Constants.TIER[problem.problemLevel - 1]
        v.setDifficultyImage(curResource)
        v.setProblemNumber(problem.problemNumber.toString())
        v.setProblemTitle(problem.title)
    }

    private fun refreshStrong() {

    }

    private fun refreshWeak() {

    }

    private fun refreshSimilar() {

    }
    
    private fun initBookmarkClick() = with(binding) {
        recommendStrongBookmarkButton1.setOnCheckedChangeListener { compoundButton, isChecked ->
            viewModel.postProblemLike(
                Problem(
                    problemId = -1L,
                    userId = ApplicationClass.preferencesHelper.prefUserId,
                    problemLike = isChecked,
                ),
            )
        }
    }

    private fun onViewStateChange(result: RecommendUIModel) {
        if (result.isRedelivered) return
        when (result) {
            is RecommendUIModel.Error -> handleErrorMessage(result.error)
            RecommendUIModel.Loading -> handleLoading(true)
            is RecommendUIModel.Success -> {
                handleLoading(false)
                result.data.let { problem ->
                    // setProblem(problem)
                }
            }
        }
    }
}
