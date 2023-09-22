package com.d204.algo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import com.d204.algo.base.BaseViewModel
import com.d204.algo.data.model.Problem
import com.d204.algo.data.repository.ProblemRepository
import com.d204.algo.presentation.utils.CoroutineContextProvider
import com.d204.algo.presentation.utils.ExceptionHandler
import com.d204.algo.presentation.utils.UiAwareLiveData
import com.d204.algo.presentation.utils.UiAwareModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

sealed class RecommendUIModel : UiAwareModel() {
    object Loading : RecommendUIModel()
    data class Error(var error: String = "") : RecommendUIModel()
    data class Success(val data: List<Problem>) : RecommendUIModel()
}

private const val TAG = "RecommendFragmentViewModel"

@HiltViewModel
class RecommendFragmentViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val problemRepository: ProblemRepository, // -> Module에 @provide로 impl return 하는 함수 있어야함
) : BaseViewModel(contextProvider) {
    // 어떤 UIModel에서 에러가 났는지 표시하기 위해 사용
    private var errorSite = 0

    // 30개를 가져오고 랜덤으로 돌려서 3개씩 보여주기
    private val _strongList = UiAwareLiveData<RecommendUIModel>()
    var strongList: LiveData<RecommendUIModel> = _strongList

    // 30개를 가져오고 랜덤으로 돌려서 3개씩 보여주기
    private val _weakList = UiAwareLiveData<RecommendUIModel>()
    var weakList: LiveData<RecommendUIModel> = _weakList

    // 30개를 가져오고 랜덤으로 돌려서 3개씩 보여주기
    private val _similarList = UiAwareLiveData<RecommendUIModel>()
    var similarList: LiveData<RecommendUIModel> = _similarList

    override val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        when (errorSite) {
            1 -> _strongList.postValue(RecommendUIModel.Error(exception.message ?: "Error"))
            2 -> _weakList.postValue(RecommendUIModel.Error(exception.message ?: "Error"))
            3 -> _similarList.postValue(RecommendUIModel.Error(exception.message ?: "Error"))
            else -> Log.d(TAG, "예기치 못한 에러 : $message")
        }
    }

    fun postProblemLike(problem: Problem) {
        launchCoroutineIO {
            problemRepository.postLikeProblems(problem)
        }
    }

    fun getStrongs(): LiveData<RecommendUIModel> {
        return strongList
    }

    fun getWeaks(): LiveData<RecommendUIModel> {
        return weakList
    }

    fun getSimilars(): LiveData<RecommendUIModel> {
        return similarList
    }

    fun getStrongList(userId: Long): LiveData<RecommendUIModel> {
        errorSite = 1
        _strongList.postValue(RecommendUIModel.Loading)
        launchCoroutineIO {
            loadStrongList(userId)
        }
        return strongList
    }

    private suspend fun loadStrongList(userId: Long) {
        problemRepository.getStrongProblems(userId).collect {
            _strongList.postValue(RecommendUIModel.Success(it))
        }
    }

    fun getWeakList(userId: Long): LiveData<RecommendUIModel> {
        errorSite = 2
        _weakList.postValue(RecommendUIModel.Loading)
        launchCoroutineIO {
            loadStrongList(userId)
        }
        return weakList
    }

    private suspend fun loadWeakList(userId: Long) {
        problemRepository.getStrongProblems(userId).collect {
            _weakList.postValue(RecommendUIModel.Success(it))
        }
    }

    fun getSimilarList(userId: Long): LiveData<RecommendUIModel> {
        errorSite = 3
        _similarList.postValue(RecommendUIModel.Loading)
        launchCoroutineIO {
            loadStrongList(userId)
        }
        return similarList
    }

    private suspend fun loadSimilarList(userId: Long) {
        problemRepository.getStrongProblems(userId).collect {
            _similarList.postValue(RecommendUIModel.Success(it))
        }
    }
}
