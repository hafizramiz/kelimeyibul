package com.example.kelimeyibul.ui.theme.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kelimeyibul.R
import com.example.kelimeyibul.data.WordEntity
import com.example.kelimeyibul.ui.theme.WordDetails
import com.example.kelimeyibul.ui.theme.WordUiState
import com.example.kelimeyibul.ui.theme.WordViewModel
import com.example.kelimeyibul.ui.theme.components.WordAppBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordEntryScreen(
    myViewModel: WordViewModel = viewModel(factory = WordViewModel.myFactory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
        WordAppBar(title = "Word Entry View",
            canNavigateBack = true,
            )
    }) { innerPadding->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        ) {
            WordEntryForms(
                wordUiState = myViewModel.wordUiState,
                onValueChange = myViewModel::updateUiState,
                enabled = myViewModel.wordUiState.isEntryValid
            )
            Button(
                onClick = {
// Save metotunu burda cagirdim
                    coroutineScope.launch {
                        myViewModel.saveWord()
                    }
                },
                // Buton baslangicta deaktif olacak. Validate olunca aktiflesecek.
                enabled = myViewModel.wordUiState.isEntryValid,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.save_action))
            }
            Spacer(modifier = Modifier.height(30.dp))
            ShowAllWord(myViewModel = myViewModel)
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordEntryForms(
//    itemDetails: ItemDetails,
//    modifier: Modifier = Modifier,
    onValueChange: (WordDetails) -> Unit = {},
    wordUiState: WordUiState,
    enabled: Boolean
) {
    // Once ui State elde etcem
    Column(
        // modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {

        OutlinedTextField(
            value = wordUiState.wordDetails.word,
            onValueChange = {
                // Burda Callback yardimiyla yaptim. Yukarda da view modeldeki fonksiyona referans verdim
                onValueChange(wordUiState.wordDetails.copy(word = it))
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                disabledBorderColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = wordUiState.wordDetails.meaning,
            onValueChange = {
                // Burda Callback yardimiyla yaptim. Yukarda da view modeldeki fonksiyona referans verdim
                onValueChange(wordUiState.wordDetails.copy(meaning = it))
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                disabledBorderColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
            singleLine = true
        )
        if (!enabled) {
            Text(
                text = stringResource(R.string.required_fields),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}


@Composable
fun ShowAllWord(myViewModel: WordViewModel) {
    // Artik bu metotun getFullWords metotunun dindurdugu Flow nesnesini burda collect etcem.
//    Buna state' i collect etmek deniyor.
    val myFullWords = myViewModel.getFullWords().collectAsState(emptyList())
    //

    Column {
        Text(
            text = "Hello my Full Words: ${myFullWords.value}!",
        )

    }

}

