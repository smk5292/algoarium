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
        memoTitleTextView.text = "아니 세상에 현우형만 합격할 줄이야"
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
                                title = "타이틀",
                                message = "메시지",
                                textPositive = "긍정",
                                positiveListener = {  },
                                textNegative = "부정",
                                negativeListener = {  },
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
            registerButtonClick()
        }
    }

    // 메모 등록 버튼 클릭
    private fun registerButtonClick() {
    }
}
