package br.com.fiap.gustavofernandez_rm94382.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EcoDicaViewModelFactory(private val application: Application) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EcoDicaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EcoDicaViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}