package com.example.globoplay_desafio_mobile.presentation.principal.my_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globoplay_desafio_mobile.data.local.entity.MediaEntity
import com.example.globoplay_desafio_mobile.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MyListViewModel
@Inject constructor(
    useCases: UseCases
) : ViewModel() {

    val medias: StateFlow<List<MediaEntity>> = useCases.getFavMediaUseCase()
        .catch { exception ->
            exception.printStackTrace()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )
}