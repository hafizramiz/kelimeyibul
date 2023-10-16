package com.example.kelimeyibul.ui.theme.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.kelimeyibul.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun WordAppBar(
    title: String,
    canNavigateBack:Boolean,
    modifier: Modifier=Modifier,
    navigateFonc: ()->Unit
){

    CenterAlignedTopAppBar(
        title = { Text(text = title)},
        navigationIcon = {
            IconButton(onClick = {
                // Burda geriye navigate yapcam
                navigateFonc
            }) {
                Icon(imageVector = Icons.Outlined.ArrowBack,
                contentDescription = stringResource(R.string.back_icon)
                )
            }
        }
    )
}