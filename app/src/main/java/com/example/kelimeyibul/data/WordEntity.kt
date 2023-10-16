package com.example.kelimeyibul.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity olustur
@Entity(tableName = "WordsTable")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @NonNull
    @ColumnInfo(name = "word")
    val word: String,

    @NonNull
    @ColumnInfo(name = "meaning")
    val meaning: String
)

// Dao katmanini yaz
