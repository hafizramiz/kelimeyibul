package com.example.kelimeyibul.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("""
        SELECT * FROM WordsTable  
        ORDER BY word ASC    
        """)
fun getAllWords(): Flow<List<WordEntity>>

@Query("""Select * From WordsTable Where id= :id""")
fun getWord(id: Int): Flow<WordEntity>

@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun insert(word: WordEntity)

@Update
suspend fun  update(word: WordEntity)

@Delete
suspend fun  delete(word: WordEntity)

}