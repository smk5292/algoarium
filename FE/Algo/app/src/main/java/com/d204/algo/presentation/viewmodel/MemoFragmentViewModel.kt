package com.d204.algo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.d204.algo.base.BaseViewModel
import com.d204.algo.presentation.utils.CoroutineContextProvider
import com.d204.algo.presentation.utils.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class MemoFragmentViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    // private val userRepository: UserRepository // -> Module에 @provide로 impl return 하는 함수 있어야함
) : BaseViewModel(contextProvider) {
    override val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        // _character.postValue(CharacterDetailUIModel.Error(exception.message ?: "Error"))
    }

    var memoContent: String = ""
    var registeredMemoContent: String = ""

    fun setMemo(content: String) {
        memoContent = content
    }
}
