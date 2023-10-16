package com.example.kelimeyibul.ui.theme

import android.app.Application
import com.example.kelimeyibul.data.WordDatabase

// Burdda  Application'dan turetip uygulama acilirken db baslatacagim.
class WordApplication : Application() {
    // Burda lazy olmasinin sebebi database'e ihtiyac oldugunda olusturulacak olmasidir.
    // Ihtiyac olmazsa ornegin herhengi sorgu atip veriyi ekranda gostermezsek
    // db olusmuyor. Bunu test ettim.

    val database: WordDatabase by lazy {
        WordDatabase.getDatabase(this)
    }
}