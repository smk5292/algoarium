package com.d204.algo.ui.status

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.d204.algo.ApplicationClass
import com.d204.algo.R
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentStatusBinding
import com.d204.algo.presentation.viewmodel.HomeFragmentViewModel
import com.d204.algo.ui.adapter.StatusAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "Algo_StatusFragment"

@AndroidEntryPoint
class StatusFragment : BaseFragment<FragmentStatusBinding, BaseViewModel>() {
    override fun getViewBinding(): FragmentStatusBinding = FragmentStatusBinding.inflate(layoutInflater)
    override val viewModel: HomeFragmentViewModel by viewModels()

    @Inject
    lateinit var statusAdapter: StatusAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        test()
        init()
    }

    // 테스트용 함수, 데이터 연결 후 제거
    private fun test() = with(binding) {
        statusRankImage.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_status_to_navigation_memo)
        }

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
        initViewPager()
    }

    private fun initData() = with(binding) {
        statusProfileUsername.text = ApplicationClass.preferencesHelper.prefUserNickname
        Glide.with(requireContext())
            .load(ApplicationClass.preferencesHelper.prefUserProfile)
            .into(statusProfileImg)
    }

    // 찜한 문제 리스트 초기화
    private fun initViewPager() = with(binding) {
        statusRecyclerView.adapter = statusAdapter.apply {
            // list = listOf(Status(1), Status(2), Status(3), Status(4), Status(5))
        }
    }
}
