package com.d204.algo.ui.status

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.d204.algo.ApplicationClass
import com.d204.algo.R
import com.d204.algo.base.BaseFragment
import com.d204.algo.base.BaseViewModel
import com.d204.algo.databinding.FragmentMemoBinding
import com.d204.algo.presentation.viewmodel.MemoFragmentViewModel
import com.d204.algo.ui.extension.showDialog
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
        memoTitleTextView.text = "아기상어 뚜루루뚜루 귀여운 뚜루루뚜루"
        memoTitleTextView.isSelected = true
    }

    private fun init() = with(binding) {
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
                        if (memoEditText.text.toString() != viewModel.registeredMemoContent) {
                            // 작성한 메모를 등록하지 않고 나갈때
                            showDialog(
                                title = "저장하시겠습니까?",
                                textPositive = "저장",
                                positiveListener = { registerMemo() },
                                textNegative = "저장하지 않고 나가기",
                                negativeListener = { findNavController().navigateUp() },
                            )
                        } else {
                            findNavController().navigateUp()
                        }
                    }
                }
            },
        )

        // 등록 버튼
        memoRegisterButton.setOnClickListener {
            registerMemo()
        }
    }

    // 메모 등록 버튼 클릭
    private fun registerMemo() {
        viewModel.updateMemo(
            1L,
            ApplicationClass.preferencesHelper.prefUserId,
            binding.memoEditText.text.toString(),
        )
    }
}
