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

        binding.statusRadarChartView
            .setDataList(
                arrayListOf(
                    RadarChartData(CharacteristicType.AGILITY, 76),
                    RadarChartData(CharacteristicType.ENDURANCE, 55),
                    RadarChartData(CharacteristicType.STRENGTH, 60),
                    RadarChartData(CharacteristicType.LEXIBILITY, 70),
                    RadarChartData(CharacteristicType.INTELLECT, 80),
                ),
            )
    }
}
