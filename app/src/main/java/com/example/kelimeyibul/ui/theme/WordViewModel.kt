package com.example.kelimeyibul.ui.theme

import androidx.annotation.NonNull
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.ColumnInfo
import com.example.kelimeyibul.data.WordDao
import com.example.kelimeyibul.data.WordEntity
import kotlinx.coroutines.flow.Flow

//ViewModel'in oluşturulduğunda constructor'a parametere vermeye izin vermedigi icin
// ViewModelProvider.Factory  kullaniyoruz.

//Bunu nasil yapcaz?
//Componaion object kullancaz. Componaion object neydi? Statik alanlari bunun icinde tanimlariz ve
// singleton olusturmak icin kullanilir.Yeni bir nesne olusturmadan bunu direk kullanabiliriz e tek
//bir ornegini kullaniriz. Yani singleton da yapar ayni zamanda


// UI tarafinda parametre almis bir ViewModel cagirirken boyle cagiriyorum.
//    viewModel: BusScheduleViewModel = viewModel(factory = BusScheduleViewModel.factoryDeneme)
// Companion object icindeki alanlar statik alanlardir. SinifIsmi.varIsmi seklinde cagirabilirim
// icindeki initializer bir tane yeni BusScheduleViewModel() dondurur icinde de parametresi olur bunun
// Artik UI tarafinda buna parametre vermek zorunda kalmayiz. Biz bu factoryDeneme isimli degiskeni
// cagirirsak o zaten BusScheduleViewModel tipinde bir nesne dondurmus olcak.
//companion object {
//    val factoryDeneme : ViewModelProvider.Factory = viewModelFactory {
//        initializer {
//            val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BusScheduleApplication)
//            println("application nesnesi olustu mu: $application")
//            BusScheduleViewModel(application.database.busScheduleDao()
//            )
//        }
//    }
//}
//}

//Dependencies varsa ViewModelProvider.Factory kullaniriz. Depedencies yoksa buna gerek kalmaz
//Dependecny den kasit parametreye ihtiyca varsa

// WordViewModel'den bir nesne uretilirse benden parametre istiyecek. Cunku primary consturctorda
// bir degisken var. Bu ne demek oluyor. Ilgili degisken bir reqired parametredir.
// private val wordDao: WordDao
class WordViewModel(private val wordDao: WordDao) : ViewModel() {
    // Bu projede repositry katmani olusturmadigim icin
// Daoyu buraya parametere olarak gectim
    var wordUiState by mutableStateOf(WordUiState())
        private set

// Verilen bilgilere gore ui state'i yeniliyor. Burda eger butun formlar dolduysa ui yenilencek

    fun updateUiState(wordDetails: WordDetails){
        // isEntryValid de true'ya donmesi icin butun formlarin dolmasi gerekiyor. Bunu baska bir fonksiyonda ele alcam
        wordUiState= WordUiState(wordDetails=wordDetails, isEntryValid = validateForms(wordDetails))
    }

    private fun validateForms(wordDetails: WordDetails=wordUiState.wordDetails): Boolean{
       return wordDetails.word.isNotBlank() && wordDetails.meaning.isNotBlank()
    }

    fun getFullWords(): Flow<List<WordEntity>> = wordDao.getAllWords()

    suspend fun saveWord() {
        // Burda bir daha validate olup olmadigini kontrol edebilim
        if(validateForms()){
            // validate ise o zaman dbye kayit at
            wordDao.insert(wordUiState.wordDetails.toWordEntity())
        }
        val a=wordUiState.wordDetails.toWordEntity()
        println("extension ile degistirdigim: $a")

    }

    //View model factory neden kullaniriz?
    companion object {
        //  Bir degisken olusturdum: ismi  myFactory  tipi ise: ViewModelProvider.Factory tipinde bir degisken
        //  val myFactory: ViewModelProvider.Factory
        // Bu viewModelFactory {  }  Initialize'ni kullanarak yeni bir view model nesnesi uretcek.

        // Yani yukardaki class'tan bir nesne uretcek. Cunku bu sinifi yani  WordViewModel sinifini
        // UI tarafinda  cagirip ona parametre gecemiyorum. Onun icin burda gecip singleton nesne uretcem.
        // Eger  WordViewModel parametere almasaydi onu direk UI tarafindan cagirabilirdim.Factory'e gerek kalmazsi
        // Cunku o zaman direk primary constructoru cagiriyordu. kendi icinde
        // sinifinin constructoru yok ve ben buna parametre gecemiyorum. Dolayisiyla onu burda uretcem
        val myFactory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // bu blok icinde View modelden yeni nesne uretcem.
                val application =
                    (this[APPLICATION_KEY] as WordApplication)
                WordViewModel(application.database.wordDao())
            }
        }

        // SImdi myFactory isimli degisken yaridimyla view model'i UI tarafinda baslatcam.
        // Ui tarafinda myFactory isimli degiskeni cagircam.
        // Bu sekilde cagircam:  myViewModel: WordViewModel = viewModel(factory = WordViewModel.myFactory)
    }
}

data class WordUiState(
    // Ici bos olan nesne urettim
    val wordDetails: WordDetails = WordDetails(),
    val isEntryValid: Boolean=false
)

data class WordDetails(
    val id: Int = 0,
    val word: String = "",
    val meaning: String = ""
)

// Worddetails'i arayuzden alcam onu WordEntity'e cevirecek extension fonksiyonu yazdim.
fun WordDetails.toWordEntity( ): WordEntity {
    return WordEntity(id = id, word = word, meaning = meaning)
}
