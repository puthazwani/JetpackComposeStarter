package com.example.jetpackcomposestarter.module

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
//    private val repo: BookRepository
) : ViewModel() {
//    val bookListState = repo.getBookList().map { bookList ->
//        try {
//            Response.Success(bookList)
//        } catch (e: Exception) {
//            Response.Failure(e)
//        }
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5_000),
//        initialValue = Response.Loading
//    )
//
//    private val _insertBookState = MutableStateFlow<InsertBookResponse>(Response.Idle)
//    val insertBookState: StateFlow<InsertBookResponse> = _insertBookState.asStateFlow()
//
//    private val _updateBookState = MutableStateFlow<UpdateBookResponse>(Response.Idle)
//    val updateBookState: StateFlow<UpdateBookResponse> = _updateBookState.asStateFlow()
//
//    private val _deleteBookState = MutableStateFlow<DeleteBookResponse>(Response.Idle)
//    val deleteBookState: StateFlow<DeleteBookResponse> = _deleteBookState.asStateFlow()


}
