package com.example.kelimeyibul.ui.theme.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kelimeyibul.R
import com.example.kelimeyibul.data.WordEntity
import com.example.kelimeyibul.ui.theme.WordViewModel
import com.example.kelimeyibul.ui.theme.components.WordAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordDetailsScreen(
    id: Int,
    myViewModel: WordViewModel = viewModel(factory = WordViewModel.myFactory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(topBar = {
        WordAppBar(
            title = "Word Entry View",
            canNavigateBack = true,
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        ) {
            // Burasi body kismidir.
            Text(text = "Tiklanan veri idsi: ${id}")
        }

    }

    Text("Word details screen :")
}