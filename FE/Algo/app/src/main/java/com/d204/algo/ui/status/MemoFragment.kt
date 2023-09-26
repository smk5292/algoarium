package com.d204.algo.ui.status

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentMemoBinding
import com.d204.algo.presentation.utils.Constants
import com.d204.algo.presentation.viewmodel.MemoFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "Algo_MemoFragment"

@AndroidEntryPoint
class MemoFragment : BaseFragment<FragmentMemoBinding, BaseViewModel>() {
    override fun getViewBinding(): FragmentMemoBinding = FragmentMemoBinding.inflate(layoutInflater)
    override val viewModel: MemoFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding) {
        initData()

        // MemoFragment로 들어왔을 때 입력란 focus 및 키보드 띄우기
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
                    } else {
                        findNavController().navigateUp()
                    }
                }
            },
        )

        // 등록 버튼
        memoRegisterButton.setOnClickListener {
            registerMemo()
        }
    }

    private fun initData() = with(binding) {
        memoProblemNumberTextView.text = requireArguments().getInt(StatusFragment.PROBLEM_NUMBER).toString()
        memoTitleTextView.text = requireArguments().getString(StatusFragment.PROBLEM_TITLE)
        memoTierImageView.setImageResource(Constants.TIER[requireArguments().getInt(StatusFragment.PROBLEM_LEVEL)])
        memoEditText.setText(requireArguments().getString(StatusFragment.PROBLEM_MEMO))
        memoTitleTextView.isSelected = true
    }

    // 메모 등록 버튼 클릭
    private fun registerMemo() {
        viewModel.updateMemo(
            requireArguments().getLong(StatusFragment.PROBLEM_ID),
            1,
//            ApplicationClass.preferencesHelper.prefUserId,
            binding.memoEditText.text.toString(),
        )
    }
}
