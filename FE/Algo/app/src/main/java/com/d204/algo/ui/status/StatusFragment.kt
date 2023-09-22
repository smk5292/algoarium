package com.d204.algo.ui.status

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.d204.algo.ApplicationClass
import com.d204.algo.R
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.data.model.Problem
import com.d204.algo.databinding.FragmentStatusBinding
import com.d204.algo.databinding.ItemStatusListBinding
import com.d204.algo.presentation.utils.Constants.TIER_COPYRIGHT_TRANSPARENT
import com.d204.algo.presentation.utils.Constants.TIER_TRANSPARENT
import com.d204.algo.presentation.viewmodel.LikeProblemsUIModel
import com.d204.algo.presentation.viewmodel.StatusFragmentViewModel
import com.d204.algo.ui.adapter.StatusAdapter
import com.d204.algo.ui.extension.observe
import com.d204.algo.ui.recommend.RecommendFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "Algo_StatusFragment"

@AndroidEntryPoint
class StatusFragment : BaseFragment<FragmentStatusBinding, BaseViewModel>() {
    companion object {
        const val PROBLEM_ID = "problemId"
        const val PROBLEM_TITLE = "problemTitle"
        const val PROBLEM_NUMBER = "problemNumber"
        const val PROBLEM_LEVEL = "problemLevel"

        @JvmStatic
        fun newInstance(problemId: Long, problemTitle: String, problemNumber: Int, problemLevel: Int) =
            RecommendFragment().apply {
                arguments = Bundle().apply {
                    putLong(PROBLEM_ID, problemId)
                    putString(PROBLEM_TITLE, problemTitle)
                    putInt(PROBLEM_NUMBER, problemNumber)
                    putInt(PROBLEM_LEVEL, problemLevel)
                }
            }
    }

    override fun getViewBinding(): FragmentStatusBinding = FragmentStatusBinding.inflate(layoutInflater)
    override val viewModel: StatusFragmentViewModel by viewModels()

    @Inject
    lateinit var statusAdapter: StatusAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        test()
        init()
    }

    // 테스트용 함수, 데이터 연결 후 제거
    private fun test() = with(binding) {
        statusAdapter.list = listOf(Problem(), Problem(), Problem(), Problem(), Problem())
        statusRadarChartView
            .setDataList(
                arrayListOf(
                    RadarChartData(CharacteristicType.WISDOM, 92),
                    RadarChartData(CharacteristicType.VITALITY, 20),
                    RadarChartData(CharacteristicType.STRENGTH, 60),
                    RadarChartData(CharacteristicType.CHARISMA, 70),
                    RadarChartData(CharacteristicType.LUCK, 80),
                ),
            )

        statusChartCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                statusRadarChartView
                    .setSameLevelDataList(
                        arrayListOf(
                            RadarChartData(CharacteristicType.WISDOM, 12),
                            RadarChartData(CharacteristicType.VITALITY, 40),
                            RadarChartData(CharacteristicType.STRENGTH, 23),
                            RadarChartData(CharacteristicType.CHARISMA, 96),
                            RadarChartData(CharacteristicType.LUCK, 7),
                        ),
                    )
            } else {
                statusRadarChartView.setSameLevelDataList(arrayListOf())
            }
        }
    }

    private fun init() {
        initData()
        initAdapter()
    }

    private fun initData() = with(binding) {
        // 상단 사용자 정보
        statusProfileUsername.text = ApplicationClass.preferencesHelper.prefUserNickname
        Glide.with(requireContext())
            .load(ApplicationClass.preferencesHelper.prefUserProfile)
            .into(statusProfileImg)
        statusRankImage.setImageResource(
            if (ApplicationClass.skinOn) {
                TIER_TRANSPARENT[ApplicationClass.preferencesHelper.prefUserTier]
            } else {
                TIER_COPYRIGHT_TRANSPARENT[ApplicationClass.preferencesHelper.prefUserTier]
            },
        )

        // 좋아요한 문제 리스트 조회
        observe(viewModel.likeProblems, ::onViewStateChange)
        viewModel.getLikeProblems(ApplicationClass.preferencesHelper.prefUserId)
    }

    private fun initAdapter() = with(binding) {
        statusAdapter.setStatusClickListener(object : StatusAdapter.StatusClickListener {
            override fun bookmarkClick(
                binding: ItemStatusListBinding,
                problem: Problem,
                isChecked: Boolean,
                position: Int,
            ) {
                Log.d(TAG, "bookmarkClick: $isChecked")
                viewModel.postProblemLike(
                    Problem(
                        problemId = problem.id,
                        userId = ApplicationClass.preferencesHelper.prefUserId,
                        problemLike = isChecked,
                    ),
                )
            }
            override fun memoClick(
                binding: ItemStatusListBinding,
                problem: Problem,
                position: Int,
            ) {
                val fragment = newInstance(problem.id, problem.title, problem.problemNumber, problem.problemLevel)
                findNavController().navigate(R.id.action_navigation_status_to_navigation_memo, fragment.arguments)
            }
        })

        statusRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = statusAdapter
        }
    }

    // 좋아요한 문제 리스트 조회
    private fun onViewStateChange(result: LikeProblemsUIModel) = with(binding) {
        if (result.isRedelivered) return
        when (result) {
            is LikeProblemsUIModel.Error -> handleErrorMessage(result.error)
            LikeProblemsUIModel.Loading -> handleLoading(true)
            is LikeProblemsUIModel.Success -> {
                handleLoading(false)
                statusAdapter.list = result.data
            }
        }
    }
}
