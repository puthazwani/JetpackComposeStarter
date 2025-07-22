package com.example.jetpackcomposestarter

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.LaunchedEffect

sealed class Response<out T> {
    data object Idle : Response<Nothing>()

    data object Loading : Response<Nothing>()

    data class Success<out T>(
        val data: T
    ) : Response<T>()

    data class Failure(
        val e: Exception
    ) : Response<Nothing>()
}

@Composable
fun <T> ResponseHandler(
    response: Response<T>,
    onSuccess: (T) -> Unit,
    onFailure: (String) -> Unit,
    onLoading: @Composable (() -> Unit)? = null,
    resetState: () -> Unit = {}
) {
    val context = LocalContext.current
    when (response) {
        is Response.Idle -> Unit
        is Response.Loading -> onLoading?.invoke()
        is Response.Success -> {
            LaunchedEffect(Unit) {
                onSuccess(response.data)
                resetState()
            }
        }
        is Response.Failure -> {
            val errorMessage = response.e.message ?: "Unknown error"
            LaunchedEffect(errorMessage) {
                onFailure(errorMessage)
                resetState()
            }
        }
    }
}