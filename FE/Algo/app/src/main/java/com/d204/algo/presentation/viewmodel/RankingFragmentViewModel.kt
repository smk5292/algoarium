package com.d204.algo.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.d204.algo.base.BaseViewModel
import com.d204.algo.data.model.Ranking
import com.d204.algo.data.repository.RankingRepository
import com.d204.algo.presentation.utils.CoroutineContextProvider
import com.d204.algo.presentation.utils.ExceptionHandler
import com.d204.algo.presentation.utils.UiAwareLiveData
import com.d204.algo.presentation.utils.UiAwareModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

sealed class RankingUIModel : UiAwareModel() {
    object Loading : RankingUIModel()
    data class Error(var error: String = "") : RankingUIModel()
    data class Success(val data: List<Ranking>) : RankingUIModel()
}

sealed class SingleRankingUIModel : UiAwareModel() {
    object Loading : SingleRankingUIModel()
    data class Error(var error: String = "") : SingleRankingUIModel()
    data class Success(val data: Ranking) : SingleRankingUIModel()
}

@HiltViewModel
class RankingFragmentViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val rankingRepository: RankingRepository, // -> Module에 @provide로 impl return 하는 함수 있어야함
) : BaseViewModel(contextProvider) {

    private val _rankingList = UiAwareLiveData<RankingUIModel>()
    var rankingList: LiveData<RankingUIModel> = _rankingList

    private val _topRanking= UiAwareLiveData<SingleRankingUIModel>()
    var topRanking: LiveData<SingleRankingUIModel> = _topRanking

    private val _myRanking = UiAwareLiveData<SingleRankingUIModel>()
    var myRanking: LiveData<SingleRankingUIModel> = _myRanking

    override val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _rankingList.postValue(RankingUIModel.Error(exception.message ?: "Error"))
    }

    fun getRankingList(tier: Int): LiveData<RankingUIModel> {
        _rankingList.postValue(RankingUIModel.Loading)
        launchCoroutineIO {
            loadRankingList(tier)
        }
        return rankingList
    }

    private suspend fun loadRankingList(tier: Int) {
        rankingRepository.getRankingsByTier(tier).collect {
            _rankingList.postValue(RankingUIModel.Success(it))
        }
    }

    fun getTopRanking(tier: Int): LiveData<SingleRankingUIModel> {
        _topRanking.postValue(SingleRankingUIModel.Loading)
        launchCoroutineIO {
            loadTopRanking(tier)
        }
        return topRanking
    }

    private suspend fun loadTopRanking(tier: Int) {
        rankingRepository.getRankingTop(tier).collect {
            _topRanking.postValue(SingleRankingUIModel.Success(it))
        }
    }

    fun getMyRanking(userId: Long): LiveData<SingleRankingUIModel> {
        _myRanking.postValue(SingleRankingUIModel.Loading)
        launchCoroutineIO {
            loadMyRanking(userId)
        }
        return myRanking
    }

    private suspend fun loadMyRanking(userId: Long) {
        rankingRepository.getRanking(userId).collect {
            _myRanking.postValue(SingleRankingUIModel.Success(it))
        }
    }
}
