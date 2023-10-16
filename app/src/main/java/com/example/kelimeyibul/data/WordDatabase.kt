package com.example.kelimeyibul.data

import android.content.Context
import android.provider.UserDictionary.Words
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(WordEntity::class), version = 1)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        // Burda degisken tanimladim. Baslangic olarak ta null atadim.Null  olarak initializ ettim
        @Volatile
        private var Instance: WordDatabase? = null

        // Return type'i WordDatabase olan bir fonksiyon yazdim.
        fun getDatabase(context: Context): WordDatabase {
            // Instance degiskenini return etcek. Eger bu null ise (Instance? : null mi)
            // Eger  Instance degiskeni null ise o zaman syncronized blogu icinde bunu initialize et
            // syncronized(this){
            //    Eger null ise burda initialize et
            // }
            // yapiyorum.

            return Instance?: synchronized(this) {
                // Burda db olustur. Eger null ise
                Room.databaseBuilder(context, WordDatabase::class.java ,"word_database")
                    .fallbackToDestructiveMigration().build().also {
                        Instance=it
                    }
            }
        }

    }


}

