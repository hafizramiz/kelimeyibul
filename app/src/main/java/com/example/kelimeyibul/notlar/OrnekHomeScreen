package com.example.kelimeyibul.ui.theme.features

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kelimeyibul.data.WordEntity
import com.example.kelimeyibul.ui.theme.WordViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    name: String, modifier: Modifier = Modifier,
    // Burda view modelden bir nesen urettim.
    // Bu nasil oldu? View model icindeki degiskeni cagirdim. O bir nesne donduruyor. Bende
    // donen nesneyi myViewModel isimli degiskene atadim.
    myViewModel: WordViewModel = viewModel(factory = WordViewModel.myFactory)
    // Bu bu arada asagida yapamiyorum. Parametre olarak gecmek zorundayim.
    // myViewModel  WordViewModel tipinde bir degisken yani ondan uretilmis bir nesne.
    // Bunun metotlarina erisebilirim artik
) {
    val coroutineScope = rememberCoroutineScope()
    // Artik bu metotun getFullWords metotunun dindurdugu Flow nesnesini burda collect etcem.
//    Buna state' i collect etmek deniyor.
    val myFullWords = myViewModel.getFullWords().collectAsState(emptyList())
    //

    Column {
        Text(
            text = "Hello my Full Words: ${myFullWords.value}!",
            modifier = modifier
        )

        ElevatedButton(
            onClick = {
                println("ekleye tiklandi")
                coroutineScope.launch {
                    // Bu haliye db ye veri girisi yapabiliyorum.
                    println("Launch basladi")
                    delay(3000);
                    val word: WordEntity = WordEntity(1, "Birinci Word", "Birinci Meaning")
                   // myViewModel.saveWord(word)
                    println("ekleme yapildi mi?")
                }

            }) {
            Text(text = "Ekle")
        }
    }

}