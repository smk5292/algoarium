package com.d204.algo.ui.status

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.d204.algo.R
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentStatusBinding
import com.d204.algo.presentation.viewmodel.HomeFragmentViewModel
import com.d204.algo.ui.adapter.StatusAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

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
        statusProfileUsername.text = "홍길동"
        statusRankImage.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_status_to_navigation_memo)
        }

        statusRadarChartView
            .setDataList(
                arrayListOf(
                    RadarChartData(CharacteristicType.AGILITY, 92),
                    RadarChartData(CharacteristicType.ENDURANCE, 20),
                    RadarChartData(CharacteristicType.STRENGTH, 60),
                    RadarChartData(CharacteristicType.LEXIBILITY, 70),
                    RadarChartData(CharacteristicType.INTELLECT, 80),
                ),
            )

        statusChartCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                statusRadarChartView
                    .setSameLevelDataList(
                        arrayListOf(
                            RadarChartData(CharacteristicType.AGILITY, 12),
                            RadarChartData(CharacteristicType.ENDURANCE, 40),
                            RadarChartData(CharacteristicType.STRENGTH, 23),
                            RadarChartData(CharacteristicType.LEXIBILITY, 96),
                            RadarChartData(CharacteristicType.INTELLECT, 7),
                        ),
                    )
            } else {
                statusRadarChartView.setSameLevelDataList(arrayListOf())
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun init() {
        initViewPager()
    }

    private fun initViewPager() = with(binding) {
        statusRecyclerView.adapter = statusAdapter.apply {
            // list = listOf(Status(1), Status(2), Status(3), Status(4), Status(5))
        }
    }
}
