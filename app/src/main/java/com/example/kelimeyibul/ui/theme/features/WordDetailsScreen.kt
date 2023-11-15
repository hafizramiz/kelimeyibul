package com.example.kelimeyibul.ui.theme.features

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kelimeyibul.R
import com.example.kelimeyibul.data.WordEntity
import com.example.kelimeyibul.ui.theme.WordViewModel
import com.example.kelimeyibul.ui.theme.components.WordAppBar
import androidx.lifecycle.createSavedStateHandle
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordDetailsScreen(
    clickedWordEntity: WordEntity?,
    wordDetailsViewModel: WordDetailsViewModel,
    onBack: () -> Unit,
    wordId: Int
) {

    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
        WordAppBar(
            title = "Word Entry View",
            canNavigateBack = true,
            // Burasi beklesin
            navigateUp = { onBack() }
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_medium)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
                ) {
                    if (clickedWordEntity == null)
                        AlertDialog(
                            onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ })
                    else
                        Row {
                            Text(text = clickedWordEntity.word)
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = clickedWordEntity.meaning, fontWeight = FontWeight.Bold)
                        }
                }
            }

            OutlinedButton(
                onClick = { deleteConfirmationRequired = true },
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.delete))
            }
            if (deleteConfirmationRequired) {
                DeleteConfirmationDialog(
                    onDeleteConfirm = {
                        deleteConfirmationRequired = false
                        // Burda delete islemini yapcam.
                        coroutineScope.launch {
                            wordDetailsViewModel.delete(clickedWordEntity!!)
                            onBack()
                        }
                    },
                    onDeleteCancel = { deleteConfirmationRequired = false },
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { /* Do nothing */ },
        title = { Text(stringResource(R.string.attention)) },
        text = { Text(stringResource(R.string.delete_question)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(R.string.yes))
            }
        })
}