package io.github.xtoolkit.mir.user.presentation.start

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.xtoolkit.mir.user.core.domain.User
import io.github.xtoolkit.mir.user.core.interactor.GetCurrentUserUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartViewModel(private val getCurrentUserUC: GetCurrentUserUC) : ViewModel() {
    var user by mutableStateOf<User?>(null)

    fun start() = viewModelScope.launch(Dispatchers.IO) {
        if (user == null) getCurrentUserUC(Unit).onSuccess { user = it }
    }
}