package com.d204.algo.ui.status

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentStatusBinding
import com.d204.algo.presentation.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatusFragment : BaseFragment<FragmentStatusBinding, BaseViewModel>() {
    override fun getViewBinding(): FragmentStatusBinding = FragmentStatusBinding.inflate(layoutInflater)
    override val viewModel: HomeFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() = with(binding) {
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
}
