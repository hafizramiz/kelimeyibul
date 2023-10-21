package com.example.kelimeyibul.ui.theme.features

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kelimeyibul.R
import com.example.kelimeyibul.data.WordEntity
import com.example.kelimeyibul.ui.theme.WordViewModel
import com.example.kelimeyibul.ui.theme.components.MyFloatingActionButton
import com.example.kelimeyibul.ui.theme.components.WordAppBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    myViewModel: WordViewModel,
    navigateToWordEntryScreen: () -> Unit,
    // Bunu her bir kelimeye tiklaninca calistircam.
    navigateToWordDetails: (Int) -> Unit

) {
    // Artik bu metotun getFullWords metotunun dindurdugu Flow nesnesini burda collect etcem.
//    Buna state' i collect etmek deniyor.
    val myFullWords by myViewModel.getFullWords().collectAsState(emptyList())
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    //
    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        WordAppBar(title = "Home Screen", canNavigateBack = false)
    }, floatingActionButton = {
        MyFloatingActionButton(navigateToWordEntryScreen = navigateToWordEntryScreen)
    }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HomeBody(myFullWords = myFullWords, onItemClick = navigateToWordDetails)
        }
    }
}


@Composable
fun HomeBody(
    myFullWords: List<WordEntity>, onItemClick: (id: Int) -> Unit
) {
    if (myFullWords.isEmpty()) {
        Text(
            text = stringResource(R.string.no_word_description),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
    } else {
        WordLazyList(
            myFullWords = myFullWords, onItemClick = { onItemClick(it.id) }
        )
    }
}


@Composable
fun WordLazyList(
    myFullWords: List<WordEntity>, onItemClick: (wordEntity: WordEntity) -> Unit
) {
    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Text(text = stringResource(R.string.word), style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = stringResource(R.string.meaning), style = MaterialTheme.typography.titleLarge)
        }
        LazyColumn(
            modifier = Modifier
        ) {
            items(
                items = myFullWords,
            ) { myWordEntity ->
// Burda card'i clickable yaptim. Ve icindeki fonksiyona bir wordEntity nesnesi verdim.
                Card(
                    modifier = Modifier
                        .clickable {
                            onItemClick(myWordEntity)
                        }
                        .padding(10.dp), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = myWordEntity.word,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = myWordEntity.meaning,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }

            }
        }
    }

}