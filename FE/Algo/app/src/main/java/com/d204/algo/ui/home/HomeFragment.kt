package com.d204.algo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d204.algo.R
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentHomeBinding
import com.d204.algo.presentation.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, BaseViewModel>() {
    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override val viewModel: HomeFragmentViewModel by viewModels()

//    @Inject
//    lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setupClickListener()
        return binding.root
    }

    private fun setupClickListener() {
        binding.apply {
            homeRecommend.setOnClickListener {
                Log.d(TAG, "setupClickListener: 눌림")
                findNavController().navigate(R.id.action_navigation_home_to_navigation_recommend)
            }

            homeRanking.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_home_to_navigation_ranking)
            }

            homeMypage.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_home_to_navigation_status)
            }
        }
    }
}
