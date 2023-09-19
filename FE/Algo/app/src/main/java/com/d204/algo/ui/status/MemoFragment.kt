package com.d204.algo.ui.status

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d204.algo.R
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentMemoBinding
import com.d204.algo.presentation.viewmodel.MemoFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "Algo_MemoFragment"

@AndroidEntryPoint
class MemoFragment : BaseFragment<FragmentMemoBinding, BaseViewModel>() {
    override fun getViewBinding(): FragmentMemoBinding = FragmentMemoBinding.inflate(layoutInflater)
    override val viewModel: MemoFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        test()
        init()
    }

    private fun test() = with(binding) {
        memoTierImageView.setImageResource(R.drawable.tier1)
        memoProblemNumberTextView.text = "19999"
        memoTitleTextView.text = "아니 세상에 현우형만 합격할 줄이야"
        memoTitleTextView.isSelected = true
    }

    private fun init() = with(binding) {
        memoEditText.requestFocus()
        (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(memoEditText, 0)

        // 뒤로가기
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (memoEditText.hasFocus()) {
                        memoEditText.clearFocus()
                        viewModel.setMemo(memoEditText.text.toString())
                    } else {
                        findNavController().navigateUp()
                    }
                }
            },
        )

        viewModel.memoContent.observe(viewLifecycleOwner) {
        }
    }
}
