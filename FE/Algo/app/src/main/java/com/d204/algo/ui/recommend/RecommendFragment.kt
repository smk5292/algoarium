package com.d204.algo.ui.recommend

import android.os.Bundle
import android.util.Log
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
import com.d204.algo.ui.extension.showSnackBar
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
        val curResource = Constants.TIER[problem.problemLevel - 1] // 여기에 잘못된 값이 들어가면 아래 부분도 실행안됨
        v.setDifficultyImage(curResource)
        v.setProblemNumber(problem.problemNumber.toString())
        v.setProblemTitle(problem.title)
    }

    private fun refreshStrong(list: List<Problem>) {
        try {
            with(binding) {
                for (i in 0 until minOf(list.size, 3)) {
                    setProblem(when(i) {
                        0 -> recommendStrong1
                        1 -> recommendStrong2
                        2 -> recommendStrong3
                        else -> throw IndexOutOfBoundsException("없는 인덱스: $i")
                    }, list[i])
                }
            }
        } catch (e: Exception) {
            showSnackBar(binding.root, "서버가 혼잡합니다. 나중에 다시 시도해주세요.")
        }
    }

    private fun refreshWeak(list: List<Problem>) {
        try {
            with(binding) {
                for (i in 0 until minOf(list.size, 3)) {
                    setProblem(when(i) {
                        0 -> recommendWeak1
                        1 -> recommendWeak2
                        2 -> recommendWeak3
                        else -> throw IndexOutOfBoundsException("없는 인덱스: $i")
                    }, list[i])
                }
            }
        } catch (e: Exception) {
            showSnackBar(binding.root, "서버가 혼잡합니다. 나중에 다시 시도해주세요.")
        }
    }

    private fun refreshSimilar(list: List<Problem>) {
        try {
            with(binding) {
                for (i in 0 until minOf(list.size, 3)) {
                    setProblem(when(i) {
                        0 -> recommendLike1
                        1 -> recommendLike2
                        2 -> recommendLike3
                        else -> throw IndexOutOfBoundsException("없는 인덱스: $i")
                    }, list[i])
                }
            }
        } catch (e: Exception) {
            showSnackBar(binding.root, "서버가 혼잡합니다. 나중에 다시 시도해주세요.")
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
                viewModel.setConstStrongs(result.data) // 정적리스트에 담아주고 // // setConstStrongs() 안에 loadConstStrongList() 정적리스트 가져오면  3개만 담는 추천 리스트에 postValue
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
                val subList = result.data.subList(0, minOf(result.data.size, 3))
                refreshStrong(subList)
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
                val subList = result.data.subList(0, minOf(result.data.size, 3))
                refreshWeak(subList)
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
                val subList = result.data.subList(0, minOf(result.data.size, 3))
                refreshSimilar(subList)
            }
        }
    }
}
