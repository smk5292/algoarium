package com.d204.algo.ui.ranking

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.d204.algo.ApplicationClass
import com.d204.algo.R
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentRankingBinding
import com.d204.algo.presentation.viewmodel.RankingFragmentViewModel
import com.d204.algo.presentation.viewmodel.RankingUIModel
import com.d204.algo.presentation.viewmodel.SingleRankingUIModel
import com.d204.algo.ui.adapter.RankingAdapter
import com.d204.algo.ui.extension.observe
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
    private val espHelper = ApplicationClass.preferencesHelper

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뷰모델 변수에 옵저빙을 등록한다.
//        observe(viewModel.getRankingList(espHelper.prefUserTier), ::onViewRankingChange)
//        observe(viewModel.getTopRanking(espHelper.prefUserTier), ::onViewTopChange)
//        observe(viewModel.getMyRanking(espHelper.prefUserId), ::onViewMyChange)

        observe(viewModel.getRankingList(1), ::onViewRankingChange)
        observe(viewModel.getTopRanking(1), ::onViewTopChange)
        observe(viewModel.getMyRanking(1), ::onViewMyChange)

        setupRecyclerView()
        setUpAnimation()
    }

    private fun setUpAnimation() {
        val viewToAnimate = binding.fragmentRankingWantedOuter // 애니메이션을 적용할 뷰
        val targetHeight = resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._150sdp) // 200dp를 픽셀로 변환

        val animation = ValueAnimator.ofInt(0, targetHeight)
        animation.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams = viewToAnimate.layoutParams
            layoutParams.height = value
            viewToAnimate.layoutParams = layoutParams
        }
        animation.interpolator = AccelerateInterpolator()
        animation.duration = 4000 // 10초 동안 애니메이션 진행
        animation.start()
    }

    private fun setupRecyclerView() {
        binding.fragmentRankingRecyclerView.apply {
            adapter = rankingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun onViewRankingChange(result: RankingUIModel) {
        if (result.isRedelivered) return
        when (result) {
            is RankingUIModel.Error -> handleErrorMessage(result.error)
            is RankingUIModel.Loading -> handleLoading(true)
            is RankingUIModel.Success -> {
                handleLoading(false)
//                val animationController: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(binding.root.context, R.anim.recycler_animation)
//                binding.fragmentRankingRecyclerView.layoutAnimation = animationController
                rankingAdapter.list = result.data
            }
        }
    }

    private fun onViewTopChange(result: SingleRankingUIModel) {
        if (result.isRedelivered) return
        when (result) {
            is SingleRankingUIModel.Error -> handleErrorMessage(result.error)
            is SingleRankingUIModel.Loading -> handleLoading(true)
            is SingleRankingUIModel.Success -> {
                handleLoading(false)
                with(binding.fragmentRankingWantedInner) {
                    setWantedName(result.data.kakaoNickname)
                    setWantedImage(result.data.profileImage)
                    setWantedCost(result.data.score.toString())
                }
            }
        }
    }

    private fun onViewMyChange(result: SingleRankingUIModel) {
        if (result.isRedelivered) return
        when (result) {
            is SingleRankingUIModel.Error -> handleErrorMessage(result.error)
            is SingleRankingUIModel.Loading -> handleLoading(true)
            is SingleRankingUIModel.Success -> {
                handleLoading(false)
                binding.fragmentRankingRecyclerView.scrollToPosition(result.data.ranking - 1)
            }
        }
    }
}
