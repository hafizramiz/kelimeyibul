package com.example.kelimeyibul.ui.theme.features

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kelimeyibul.data.WordDao
import com.example.kelimeyibul.data.WordEntity
import com.example.kelimeyibul.ui.theme.WordApplication
import com.example.kelimeyibul.ui.theme.WordDetails
import com.example.kelimeyibul.ui.theme.WordViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class WordDetailsViewModel(
//    savedStateHandle: SavedStateHandle,
    private val wordDao: WordDao,
) : ViewModel() {

// db metotu
    fun getWordWithId(wordId: Int): Flow<WordEntity> = wordDao.getWord(wordId)

    suspend fun delete(wordEntity: WordEntity)=wordDao.delete(wordEntity)


// Burasi viewmodeli initialize etme kodlaridir.
    companion object {
        val myFactory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                println("view model create edildi")
                // bu blok icinde View modelden yeni nesne uretcem.
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WordApplication)
                WordDetailsViewModel(
//                    this.createSavedStateHandle(),
                    application.database.wordDao(),
                )
            }
        }
    }
}

data class WordDetailsUiState(
    val wordDetails: WordDetails = WordDetails(),
)

fun WordEntity.toWordDetails():WordDetails{
    return WordDetails(id=id,meaning=meaning, word = word)
}