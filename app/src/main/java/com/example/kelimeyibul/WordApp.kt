package com.example.kelimeyibul

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.kelimeyibul.ui.theme.WordViewModel
import com.example.kelimeyibul.ui.theme.components.WordAppBar
import com.example.kelimeyibul.ui.theme.features.HomeScreen
import com.example.kelimeyibul.ui.theme.features.WordDetailsDestination
import com.example.kelimeyibul.ui.theme.features.WordDetailsScreen
import com.example.kelimeyibul.ui.theme.features.WordDetailsViewModel
import com.example.kelimeyibul.ui.theme.features.WordEntryScreen

// Navigation'i burda yonetiyorum.


// Once bir enum tanimladim.
enum class WordAppScreen() {
    Home,
    WordEntry,
    WordDetails
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordApp(
    navController: NavHostController = rememberNavController(),
    myViewModel: WordViewModel = viewModel(factory = WordViewModel.myFactory),
   wordDetailsViewModel: WordDetailsViewModel = viewModel(factory = WordDetailsViewModel.myFactory)

) {

    NavHost(navController = navController, startDestination = WordAppScreen.Home.name) {
        composable(route = WordAppScreen.Home.name) {
            HomeScreen(
                myViewModel = myViewModel,
                navigateToWordEntryScreen = {
                    // Burda navigate yapcam.Route'in ismini veriyorum. Burda yeni word girmesi icin sayfaya yonlendirdim.
                    // Bu da floating action button'da calisiyor.
                    navController.navigate(route = WordAppScreen.WordEntry.name)
                    // Peki bu ekrandan digerine giderken parametre gecmek istersem ne olcak?
//                    Yani diyelim ki kelimler var. Her bir item'a tiklaninca o item ile ilgili bilgiyi diger ekrana
                    // transfer etmek istersem. Bunu nasil yapcam. Bunu da asagidaki fonksiyonda yapcam.
                },
                navigateToWordDetails = {
                    println(it)
                    // Simdi burda lazy column icindeki her bir elemana tiklaninca gidecegi yeri yonetcem
                    // Burdan baska sayfaya atinca benim parametre de gondermem gerekiyor. Orn id bilgisini
                    // Bunun icin id'yi diger sayfaya atcam. Burdaki it id oluyor. Cunku callback fonksiyonuna
                    // ordan Int deger olarak verdim.
                    navController.navigate(
                        "${WordDetailsDestination.route}/${it}"
                    )
                },
            )
            println("Home screen")
        }
        composable(route = WordAppScreen.WordEntry.name) {
            WordEntryScreen(myViewModel = myViewModel)
            println("Home screen")
        }
//        composable(route = WordAppScreen.WordDetails.name) {
//            WordDetailsScreen()
//        }

        composable(route = WordDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(WordDetailsDestination.wordIdArg){
                type= NavType.IntType
            })
        ) {
            // Hangisine tikladiysa onun id si gerekli ki degisiklik yapabilsin.
            // Bu sayfaya giderken benden id bekliyor.
            WordDetailsScreen(wordDetailsViewModel=wordDetailsViewModel)
        }
    }
}





