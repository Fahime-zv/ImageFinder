package payback.group.imagefinder.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import payback.group.imagefinder.ui.screen.detail.DetailScreen
import payback.group.imagefinder.ui.screen.search.SearchScreen

@Composable
fun AppNavigationGraph (){

    val navController= rememberNavController()

    NavHost(navController = navController, startDestination =NavigationItem.Search.route ){
        composable(NavigationItem.Search.route){
            SearchScreen(navController=navController)
        }
        composable("${NavigationItem.Detail.route}/{itemId}"){backStackEntry->
            val itemId = backStackEntry.arguments?.getString("itemId")
            DetailScreen(navController = navController,itemId=itemId)
        }
    }

}