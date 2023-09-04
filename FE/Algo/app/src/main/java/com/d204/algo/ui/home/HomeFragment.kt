package com.d204.algo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentHomeBinding
import com.d204.algo.presentation.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, BaseViewModel>() {
    override fun getViewBinding(): FragmentHomeBinding  = FragmentHomeBinding.inflate(layoutInflater)

    override val viewModel: HomeFragmentViewModel by viewModels()

//    @Inject
//    lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }

    //  다른 프래그먼트 이동
    //  findNavController().navigate(R.id.action_signFragment_to_signDrawFragment)
}