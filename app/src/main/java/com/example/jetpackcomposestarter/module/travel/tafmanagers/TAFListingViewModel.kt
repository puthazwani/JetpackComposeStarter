package com.example.jetpackcomposestarter.module.travel.tafmanagers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposestarter.Response
import com.example.jetpackcomposestarter.data.local.entities.TravelAuthorisationEntity
import com.example.jetpackcomposestarter.data.repository.AppCoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias InsertTafFormResponse = Response<Unit>
typealias UpdateTafFormResponse = Response<Unit>
typealias DeleteTafFormResponse = Response<Unit>

@HiltViewModel
class TAFListingViewModel @Inject constructor(
    private val repo: AppCoreRepository
) : ViewModel() {
    val tafListingState = repo.getTravelAuthorisations().map {
        try {
            Response.Success(it)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = Response.Loading
    )

    private val _insertTafFormState = MutableStateFlow<InsertTafFormResponse>(Response.Idle)
    val insertTafFormState: StateFlow<InsertTafFormResponse> = _insertTafFormState.asStateFlow()

    fun insertBook(tafForm: TravelAuthorisationEntity) = viewModelScope.launch {
        try {
            _insertTafFormState.value = Response.Loading
            _insertTafFormState.value = Response.Success(repo.insertTafForm(tafForm))
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}