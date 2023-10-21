package com.example.kelimeyibul.ui.theme.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kelimeyibul.R

@Composable

fun MyFloatingActionButton(
    navigateToWordEntryScreen: ()-> Unit
){
    FloatingActionButton(
        onClick = navigateToWordEntryScreen,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(3.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.AddCircle,
            contentDescription = stringResource(R.string.back_icon)
        )
    }
}