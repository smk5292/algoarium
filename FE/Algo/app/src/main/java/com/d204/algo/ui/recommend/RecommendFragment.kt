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
        observe(viewModel.getStrongs(), ::onViewStrongListChange)
        observe(viewModel.getWeaks(), ::onViewWeakListChange)
        observe(viewModel.getSimilars(), ::onViewSimilarListChange)

        // 추천 리스트에 옵저빙 등록
        observe(viewModel.getSelectedStrongs(), ::onViewStrongChange)
        observe(viewModel.getSelectedWeaks(), ::onViewWeakChange)
        observe(viewModel.getSelectedSimilars(), ::onViewSimilarChange)

        // 억지로 주입
        viewModel.setConstStrongs(listOf(Problem()))

        // 각 30개의 추천 문제 리스트를 서버에서 가져온다 -> postValue -> 변경되면 observe에서 인지하고 binding ui를 갱신
        viewModel.getStrongList(espHelper.prefUserId)
        viewModel.getWeakList(espHelper.prefUserId)
        viewModel.getSimilarList(espHelper.prefUserId)

        // 리프레쉬 등록
        with(binding) {
            recommendStrongRefresh.setOnClickListener { viewModel.loadConstStrongList() }
            recommendWeakRefresh.setOnClickListener { viewModel.loadConstWeakList() }
            recommendLikeRefresh.setOnClickListener { viewModel.loadConstSimilarList() }
        }
    }

    private fun setProblem(v: RecommendProblemView, problem: Problem) {
        val curResource = Constants.TIER[problem.problemLevel - 1]
        v.setDifficultyImage(curResource)
        v.setProblemNumber(problem.problemNumber.toString())
        v.setProblemTitle(problem.title)
    }

    private fun refreshStrong(list: List<Problem>) {
        with(binding) {
            setProblem(recommendStrong1, list[0])
            setProblem(recommendStrong2, list[1])
            setProblem(recommendStrong3, list[2])
        }
    }

    private fun refreshWeak(list: List<Problem>) {
        with(binding) {
            setProblem(recommendWeak1, list[0])
            setProblem(recommendWeak2, list[1])
            setProblem(recommendWeak3, list[2])
        }
    }

    private fun refreshSimilar(list: List<Problem>) {
        with(binding) {
            setProblem(recommendLike1, list[0])
            setProblem(recommendLike2, list[1])
            setProblem(recommendLike3, list[2])
        }
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

    private fun onViewStrongListChange(result: RecommendUIModel) {
        if (result.isRedelivered) return
        when (result) {
            is RecommendUIModel.Error -> handleErrorMessage(result.error)
            is RecommendUIModel.Loading -> handleLoading(true)
            is RecommendUIModel.Success -> {
                handleLoading(false)
                viewModel.setConstStrongs(result.data) // 정적리스트에 담아주고
                viewModel.loadConstStrongList() // 정적리스트 가져오면  3개만 담는 추천 리스트에 postValue
            }
        }
    }

    private fun onViewWeakListChange(result: RecommendUIModel) {
        if (result.isRedelivered) return
        when (result) {
            is RecommendUIModel.Error -> handleErrorMessage(result.error)
            is RecommendUIModel.Loading -> handleLoading(true)
            is RecommendUIModel.Success -> {
                handleLoading(false)
                viewModel.setConstWeaks(result.data) // 정적리스트에 담아주고
                viewModel.loadConstWeakList()  // 최초 갱신
            }
        }
    }

    private fun onViewSimilarListChange(result: RecommendUIModel) {
        if (result.isRedelivered) return
        when (result) {
            is RecommendUIModel.Error -> handleErrorMessage(result.error)
            is RecommendUIModel.Loading -> handleLoading(true)
            is RecommendUIModel.Success -> {
                handleLoading(false)
                viewModel.setConstSimilars(result.data) // 정적리스트에 담아주고
                viewModel.loadConstSimilarList()  // 최초 갱신
            }
        }
    }

    private fun onViewStrongChange(result: RecommendUIModel) {
        if (result.isRedelivered) return
        when (result) {
            is RecommendUIModel.Error -> handleErrorMessage(result.error)
            is RecommendUIModel.Loading -> handleLoading(true)
            is RecommendUIModel.Success -> {
                handleLoading(false)
                refreshStrong(result.data.subList(0, 3))
            }
        }
    }

    private fun onViewWeakChange(result: RecommendUIModel) {
        if (result.isRedelivered) return
        when (result) {
            is RecommendUIModel.Error -> handleErrorMessage(result.error)
            is RecommendUIModel.Loading -> handleLoading(true)
            is RecommendUIModel.Success -> {
                handleLoading(false)
                refreshWeak(result.data.subList(0, 3))
            }
        }
    }

    private fun onViewSimilarChange(result: RecommendUIModel) {
        if (result.isRedelivered) return
        when (result) {
            is RecommendUIModel.Error -> handleErrorMessage(result.error)
            is RecommendUIModel.Loading -> handleLoading(true)
            is RecommendUIModel.Success -> {
                handleLoading(false)
                refreshSimilar(result.data.subList(0, 3))
            }
        }
    }
}
