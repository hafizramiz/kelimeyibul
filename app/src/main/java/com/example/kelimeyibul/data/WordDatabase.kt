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
        // Burda degisken tanimladim. Baslangic olarak ta null atadim.Null  olarak initialize ettim
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

//Room Database kullanirken @Volatile annotation'nın neden kullanıldığını biliyor muydun? Ornek olarak göstermek gerekirse:
//@Volatile
//private var Instance: AppDatabase? = null
//@Volatile anahtar kelimesi, bir değişkenin değerinin asla önbelleğe alınmadığını
// (cached) ve tüm okuma ve yazmaların ana bellekten gerçekleştirildiğini belirtir. // Peki bu ne işimize yarar?
//Instance değişkeninin değerinin her zaman güncel kalmasını ve tüm yürütme iş
// parçacıkları için aynı olmasını sağlar. Bu, bir iş parçacığı tarafından yapılan değişikliklerin
// Instance değişkenine hemen diğer tüm iş parçacıkları tarafından görülebilir olduğu anlamına gelir.
// Eğer bir değişken Volatile olarak işaretlenirse, bir iş parçacığı tarafından yapılan bir değişiklik
// derhal diğer iş parçacıkları tarafından görülebilir hale gelir, böylece değerlerin tutarsızlığından
// kaynaklanan hatalar önlenmiş olur.