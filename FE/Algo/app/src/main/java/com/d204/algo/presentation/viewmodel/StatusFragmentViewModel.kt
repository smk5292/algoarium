package com.d204.algo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import com.d204.algo.base.BaseViewModel
import com.d204.algo.data.model.Problem
import com.d204.algo.data.model.Status
import com.d204.algo.data.repository.ProblemRepository
import com.d204.algo.data.repository.StatusRepository
import com.d204.algo.presentation.utils.CoroutineContextProvider
import com.d204.algo.presentation.utils.ExceptionHandler
import com.d204.algo.presentation.utils.UiAwareLiveData
import com.d204.algo.presentation.utils.UiAwareModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

private const val TAG = "Algo_StatusFragmentViewModel"
sealed class StatusUIModel : UiAwareModel() {
    object Loading : StatusUIModel()
    data class Error(var error: String = "") : StatusUIModel()
    data class Success(val data: Status) : StatusUIModel()
}

sealed class LikeProblemsUIModel : UiAwareModel() {
    object Loading : LikeProblemsUIModel()
    data class Error(var error: String = "") : LikeProblemsUIModel()
    data class Success(val data: List<Problem>) : LikeProblemsUIModel()
}

@HiltViewModel
class StatusFragmentViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    // private val userRepository: UserRepository // -> Module에 @provide로 impl return 하는 함수 있어야함
    private val statusRepository: StatusRepository,
    private val problemRepository: ProblemRepository,
) : BaseViewModel(contextProvider) {

    override val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _statusData.postValue(StatusUIModel.Error(exception.message ?: "Error"))
    }

    private val _statusData = UiAwareLiveData<StatusUIModel>()
    var statusData: LiveData<StatusUIModel> = _statusData

    fun getUserStatus(userId: Long) {
        launchCoroutineIO {
            statusRepository.getStatus(userId).collect {
                _statusData.postValue(StatusUIModel.Success(it))
            }
        }
    }

    private val _likeProblems = UiAwareLiveData<LikeProblemsUIModel>()
    var likeProblems: LiveData<LikeProblemsUIModel> = _likeProblems

    fun getLikeProblems(userId: Long) {
        launchCoroutineIO {
            problemRepository.getLikeProblems(userId).collect {
                _likeProblems.postValue(LikeProblemsUIModel.Success(it))
            }
        }
    }

    fun postProblemLike(problem: Problem) {
        launchCoroutineIO {
            Log.d(TAG, "postProblemLike: $problem")
            problemRepository.postLikeProblems(problem).collect {
            }
        }
    }
}
