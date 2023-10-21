package com.example.kelimeyibul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kelimeyibul.ui.theme.KelimeyiBulTheme
import com.example.kelimeyibul.ui.theme.features.HomeScreen
import com.example.kelimeyibul.ui.theme.features.WordEntryScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KelimeyiBulTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     //HomeScreen()
                   // WordEntryScreen()
                    WordApp()
                }
            }
        }
    }
}


// Room depenecies ekle
// Entity sinifini olustur
// Dao sinifini olustur
// Database'i baslatacak sinifi olustur.
// Application'dan inherit alan sinifi olustur.
// Manifest dosyasini guncelle
// View modeli ve factory metotunu olustur.


