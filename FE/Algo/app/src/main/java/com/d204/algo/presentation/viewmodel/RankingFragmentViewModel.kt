package com.d204.algo.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.d204.algo.base.BaseViewModel
import com.d204.algo.data.model.Ranking
import com.d204.algo.data.repository.UserRepository
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

@HiltViewModel
class RankingFragmentViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val userRepository: UserRepository, // -> Module에 @provide로 impl return 하는 함수 있어야함
) : BaseViewModel(contextProvider) {

    private val _rankingList = UiAwareLiveData<RankingUIModel>()
    var rankingList: LiveData<RankingUIModel> = _rankingList

    override val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        // _character.postValue(CharacterDetailUIModel.Error(exception.message ?: "Error"))
    }
}
