package wolf.north.fbwmaster.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import wolf.north.fbwmaster.EntryScreen
import wolf.north.fbwmaster.comps.ExercisesListScreen
import wolf.north.fbwmaster.comps.GreetingScreen
import wolf.north.fbwmaster.comps.MainScreen
import wolf.north.fbwmaster.comps.PlansListScreen
import wolf.north.fbwmaster.comps.ToolsScreen

@Composable
fun NavigationComponent(startDestination: String = "startScreen") {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("startScreen") { EntryScreen(navController = navController) }
        composable("mainScreen") { MainScreen(navController) }
        composable("greetingScreen") { GreetingScreen(navController) }
        composable("plansListScreen") { PlansListScreen(navController) }
        composable("exercisesList") { ExercisesListScreen(navController) }
        composable("toolsScreen") { ToolsScreen(navController) }


    }
}