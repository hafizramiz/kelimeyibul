package com.example.kelimeyibul.ui.theme.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kelimeyibul.data.WordDao
import com.example.kelimeyibul.ui.theme.WordApplication
import com.example.kelimeyibul.ui.theme.WordViewModel

class WordDetailsViewModel(
    private val wordDao: WordDao,
    savedStateHandle: SavedStateHandle,
):ViewModel() {
    var wordId by mutableStateOf<Int>(0)
    //private val wordId: Int= checkNotNull(savedStateHandle[WordDetailsDestination.wordIdArg])

    companion object {
        val myFactory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                println("view model create edildi")
                // bu blok icinde View modelden yeni nesne uretcem.
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WordApplication)
                WordDetailsViewModel(application.database.wordDao(), savedStateHandle = this.createSavedStateHandle())
            }
        }
}}